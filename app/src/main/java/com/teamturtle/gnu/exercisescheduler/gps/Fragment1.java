package com.teamturtle.gnu.exercisescheduler.gps;

import android.app.Fragment;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.teamturtle.gnu.exercisescheduler.R;

import java.util.ArrayList;

import static com.teamturtle.gnu.exercisescheduler.MainActivity.mContext;

// OnMapReadyCallback : Callback interface for when the map is ready to be used.
public class Fragment1 extends Fragment implements OnMapReadyCallback {

    /**
     * 각종 변수 선언
     */
    //======== 구글맵(GPS) 기능 구동을 위한 변수들 ========//
    private LocationManager mLocationManager; // 위치관리자
    private GPSListener mGPSListener; // GPS 리스너
    private GoogleMap map;
    private MapView mapView;
    private Marker curMarker;

    //======== 이동거리, 경과시간, 현재속도, 최대속도를 나타내기 위한 변수들 ========//
    private double currentSpeed, maxSpeed;
    private double distance;
    private long currentTime, axisTime, between;
    private Location lastLocation; // 최근 위치 저장용

    //======== 근접경보 관련 변수 ========//
    private Alert_IntentReceiver mAlert_IntentReceiver;
    private ArrayList mPendingIntentList;
    private Vibrator vibe; // 목적지에 근접했을 때 기기를 진동시키기 위한 변수
    private Marker goalMarker; // 여기다 목적지 마커를 담게해서 새로 목적지 설정하면 이전 목적지 마커는 지워지도록 할 것.
    private PendingIntent pIntent; // alertRegister 함수에서 쓸 펜딩인텐트 변수
    private String intentKey = "goalProximity";
    private boolean checkIntent; // 현재 LocationManager에 ProximityAlert이 붙어 있는지 체크하는 용도의 변수. true면 현재 목표지 설정이 된 상태.

    //======== 시작, 정지, 초기화 버튼들 ========//
    private Button btnStart;
    private Button btnStop;
    private Button btnReset;

    /**
     * GPS 프래그먼트 시작(inflate)
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //======== fragment1.xml 인플레이트 ========//
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

        // 위치관리자 객체 참조
        // 현재 포함된 액티비티와의 상호작용을 위해서 getActivity 메소드를 사용.
        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        // 각 변수들 0으로 초기화
        currentSpeed = maxSpeed = 0;
        distance = 0;
        currentTime = axisTime = between = 0;

        // fragment안에 구글맵을 넣으려면 MapFragment가 아닌 MapView를 사용해야 함
        // Bundle은 안드로이드 여러곳에서 데이터를 서로 주고 받아야 할 때 사용하는 자료구조 같은 개념
        mapView = (MapView) rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState); // Create를 하기위해서는 fragment의 onCreateView의 Bundle을 전달 받아야 한다.
        mapView.onResume(); // MapView는 onCreate와 onResume을 선언해주어야 한다. 안 그러면 아무것도 안 뜸.
        mapView.getMapAsync(this); // OnMapReadyCallback을 호출하는 부분. 연결.

        //======== 각 버튼 객체들 참조 ========//

        // '시작' 버튼
        btnStart = (Button)rootView.findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOnClick();
            }
        });

        // '정지' 버튼
        btnStop = (Button)rootView.findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopOnClick();
            }
        });

        // '초기화' 버튼
        btnReset = (Button)rootView.findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetOnClick();
            }
        });

        // 목적지 설정 버튼을 터치할 시, 목적지 설정 액티비티를 띄운다.
        Button btn_address = (Button) rootView.findViewById(R.id.btn_address);
        btn_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getActivity(), Fragment1_Address.class);
                startActivity(mIntent);
            }
        });

        //======== 각 버튼 객체들 참조 끝 ========//

        //======== 근접 경보 기능을 위한 작업들 ========//
        mPendingIntentList = new ArrayList();
        vibe = (Vibrator)mContext.getSystemService(mContext.VIBRATOR_SERVICE); // 진동을 위한 Vibrator 객체 선언
        checkIntent = false;

        //======== View를 리턴 ========//
        return rootView;
    }

    /**
     * 이하 각종 메소드 및 클래스들
     */

    /**
     * 구글맵 사용할 준비 되면 onMapReady 메소드 호출 됨.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap; // GoogleMap형 변수에 googleMap을 집어넣음. 구글맵 실행.

        // 구글맵이 실행되면 초기 현재위치로 경상대학교를 나타내도록 지정
        curMarker = map.addMarker(new MarkerOptions()
                .position(new LatLng(35.154058, 128.098084))
                .title("경상대학교"));
        curMarker.showInfoWindow();
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.154058, 128.098084), 15));

        // 구글맵이 실행되면 초기 목표위치로 전혀 다른 나라(임의로 일본)를 나타내도록 지정
        goalMarker = map.addMarker(new MarkerOptions()
                .position(new LatLng(35.726235, 139.728920))
                .title("임의의 목표지점"));

        // 현재 위치 확인을 위해 정의한 메소드 호출
        startLocationService();
    }

    /**
     * GPS를 이용해 현재위치를 탐색하고 지도에 표시하는 기능들
     */

    /**
     * 현재 위치 확인(요청)을 위해 정의한 메소드
     */
    private void startLocationService() {
        // 리스너 객체 생성
        // 아래에 LocationListener로 구현한 GPSListener 클래스 정의해 둠.
        mGPSListener = new GPSListener();

        long minTime = 1000; // 1초에 한번씩 위치정보 받는다.
        float minDistance = 0;

        // 위치요청
        try {
            // GPS 기반 위치 요청
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    mGPSListener);

            // 이거 없으면 에러남
        } catch(SecurityException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * GPS 리스너 클래스 정의 - 위치 관리자가 쏜 현재 위치를 GPS 리스너(LocationListener)가 받음.
     */
    private class GPSListener implements LocationListener {
        // 받아온 위치정보로 이동거리, 경과시간, 현재속도, 최대속도 계산해서 출력해 줄 것
        TextView speedView = (TextView) getActivity().findViewById(R.id.display_speed);
        TextView distanceView = (TextView) getActivity().findViewById(R.id.display_distance);
        TextView timeView = (TextView) getActivity().findViewById(R.id.display_time);

        /**
         * 위치 정보가 확인되었을 때 호출되는 메소드
         */
        @Override
        public void onLocationChanged(Location location) {
            // getLatitude와 getLongitude를 이용해 현재위치의 위도와 경도값을 받아와 변수에 저장.
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            // 지도에 현재위치를 찍기 위한 메소드에 위도, 경도값을 넘겨주면서 호출
            showCurrentLocation(latitude, longitude);

            // 현재속도, 최대속도 구하기
            if(location != null) {
                currentSpeed = location.getSpeed();
                if (currentSpeed > maxSpeed) {
                    maxSpeed = currentSpeed;
                }
                String strMaxSpeed = String.format("%.2f", maxSpeed);
                String strMinSpeed = String.format("%.2f", currentSpeed);
                speedView.setText("최대속도 : " + strMaxSpeed + " m/s" + "\n현재속도 : " + strMinSpeed + " m/s");
            }

            // 누적 이동거리 구하기
            if(lastLocation == null) {
                lastLocation = location; // 최근 위치를 lastLocation 변수에 저장
            } else {
                distance = distance + lastLocation.distanceTo(location); // distanceTo = Returns the approximate distance in meters between this location and the given location.
                lastLocation = location; // 새롭게 갱신 된 최근 위치를 다시 lastLocation 변수에 저장
                String strDistance = String.format("%.2f", distance); // 소수점 둘째자리까지만 표시하도록

                distanceView.setText("이동거리 : " + strDistance + " m");
            }

            // 이동한 시간 구하기
            if(axisTime == 0) {
                currentTime = System.currentTimeMillis();
                axisTime = currentTime; // 기준시간을 현재시간으로 설정
            } else {
                currentTime = System.currentTimeMillis();
                between = (currentTime - axisTime) / 1000; // 1000밀리세컨드 = 1초. 즉, 1000으로 나누어주면 초가 됨.

                timeView.setText("경과시간 : " + between + " 초");
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onProviderDisabled(String provider) {}
    }

    /**
     * 지도에 현재위치를 찍기 위한 메소드 정의
     */
    private void showCurrentLocation(Double latitude, Double longitude) {
        // 현재 위치를 이용해 LatLon 객체 생성(위도, 경도값 가지고 있음)
        LatLng curPoint = new LatLng(latitude, longitude);

        curMarker.remove();

        // 현재위치에 Marker를 찍는다.
        curMarker = map.addMarker(new MarkerOptions()
                .position(curPoint)
                .title("현재위치"));
        curMarker.showInfoWindow();

        // 카메라의 정중앙에 항상 내 위치가 오게끔 하기위한 animateCamera메소드
        // 숫자가 커지면 더 크게 확대가능.
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
        // 지형도인 경우에는 GoogleMap.MAP_TYPE_TERRAIN, 위성 지도인 경우에는 GoogleMap.MAP_TYPE_SATELLITE
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    /**
     * 목적지 설정 관련 기능들
     */

    /**
     * 목적지 설정 완료되면 호출 될 메소드 정의
     * 이 메소드가 호출되면 받아 온 목적지의 위도와 경도값을 이용해 목적지를 설정하게 된다.
     * 목적지에 마커 찍은 후, 목적지에 근접하면 경보 알람이 울리도록 설정하는 메소드를 호출한다.
     */
    public void setUpGoal(Double latitude, Double longitude) {
        LatLng goalPoint = new LatLng(latitude, longitude); // 현재 위치를 이용해 LatLon 객체 생성(위도, 경도값 가지고 있음)

        goalMarker.remove(); // 새로 목표지점을 설정하면 이전 목표지점은 지운다.

        // Marker에 MarkerOptions가 포함되는 개념
        // Marker 변수 생성. 여기에 갖가지 Option들을 달아보자.
        goalMarker = map.addMarker(new MarkerOptions()
                .position(goalPoint)
                .title("목표지점"));
        // 이제 이걸 showInfoWindow 메소드를 호출해서 타이틀이 보이도록 할 거야.
        goalMarker.showInfoWindow();

        //**************** 근접 경보 관련 ******************//

        // 목적지 근접시 경보 알람 울리게끔 하는 메소드를 호출하여 경보지점을 등록한다.
        // 네 번째 매개변수가 radius 즉, 경보 울림 반지름. 최종적으로 200m, 100m, 목적지 근접(50m) 세 구간에서 알림이 뜨도록 할 예정.
        // int id, double latitude, double longitude, float radius, long expiration
        alertRegister(1001, latitude, longitude, 200, -1);
        alertRegister(1002, latitude, longitude, 100, -1);
        alertRegister(1003, latitude, longitude, 50, -1);

        // 수신자 객체 생성하여 등록
        // 여기서 생성자의 매개변수로 던져주는 intentKey가 바로 expectedAction.
        // IntentFilter에 expectedAction을 달아주는데, intentKey는 필터, 말 그대로 걸러내기 위함.
        // 따로 구현한 Receiver에 특정된 intentKey를 달아 준 채 객체화 시켜줬으니 이제 registerReceiver에 이 객체와 Filter(intentKey를 가지고 있는)를 넘겨주면? intentKey값이 동일한 방송만 골라서 잡음.
        mAlert_IntentReceiver = new Alert_IntentReceiver(intentKey);
        // Register a BroadcastReceiver to be run in the main activity thread.
        // registerReceiver(BroadcastReceiver receiver, IntentFilter filter)
        // 방송된 펜딩인텐트를 받는다. 필터에 걸러서. 선별적으로.
        getActivity().getApplicationContext().registerReceiver(mAlert_IntentReceiver, mAlert_IntentReceiver.getFilter());
    }



    /**
     ****************** 근접 경보 관련 기능들 *********************************
     */





    /**
     * 목적지점의 위도와 경도 좌표 지정하는 등록(register) 메소드
     * intent에 id, 위도, 경도 넣고 intentKey 설정한 후
     * 해당 intent를 PendingIntent의 getBroadcast에 붙임.
     * 이렇게 완성 된 intent를 LocationManager의 addProximityAlert 메소드의 인자로 던져 줌.
     * addProximityAlert은 주어진 위치에 근접하면 던져 진 PendingIntent를 발동시키는 개념.
     *
     * 즉, 이 메소드를 통해 목적지를 설정하고 방송이 송출 될 시점(근접)이 정해지면 방송이 내보내지는 구조.
     * 이 방송을 받는 게 setUpGoal 메소드 안에 있는 registerReceiver(Context 하위 메소드, MainActivity 프로세스가 실행되는 동안 작동하는 것인 듯) 인데,
     * registerReceiver의 매개변수로 intentKey를 생성자로 넘겨받은 채 객체화 된 IntentReceiver와 그 안에 있는 getFilter 메소드를 던져 줌.
     * 즉, 방송을 등록할 때 최초로 넘겨받은 key 값인 intentKey를 이용해서 방송을 송출하는데, 이 intentKey가 바로 registerReceiver에서 받을 방송을 필터링 하게 되는 key 값이로구나?!
     */
    private void alertRegister(int id, double latitude, double longitude, float radius, long expiration) {
        Intent proximityIntent = new Intent(intentKey); // 인텐트 구별용 키
        proximityIntent.putExtra("id", id);
        proximityIntent.putExtra("latitude", latitude);
        proximityIntent.putExtra("logitude", longitude);

        // PendingIntent는 인텐트를 전송하고자 하는 '송신자'가 인텐트를 하나 생성한 후, 별도의 컴포넌트에게 '이 인텐트를 나중에 나 대신 보내 주렴.' 하고 전달하고자 할 때 사용되는 클래스
        // PendingIntent의 getBroadcast를 활용하여 방송을 보냄. 그걸 아래에 있는 Alert_IntentReceiver에서 받음.
        pIntent = PendingIntent.getBroadcast(mContext, id, proximityIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Set a proximity alert for the location given by the position (latitude, longitude) and the given radius.
        // LocationManager의 addProximityAlert 메소드에 위도, 경도, 반지름, expiration 펜딩인텐트를 넘겨줌.
        // MainActivity의 권한 얻는 부분 작동하면 빨간줄 없어지고 제대로 작동.

        // When the device detects that it has entered or exited the area surrounding the location, the given PendingIntent will be used to create an Intent to be fired.
        // addProixmityAlert은 기기가 특정 범위에 들어갔을 경우, 주어진 PendingIntent가 발동되는 개념
        // 특정 위치(위도, 경도)에 근접하면 방송을 내보낸다. 왜냐? 매개변수로 전달 된 intent가 PendingIntent의 getBroadcast 이니까.
        // 여기서 제공한 PendingIntent인 intent에는 id, (id, 위도, 경도)값이 들어있는 intent, FLAG 값이 들어있고...
        mLocationManager.addProximityAlert(latitude, longitude, radius, expiration, pIntent);
        checkIntent = true; // 현재 목표설정이 되었다는 표시.

        mPendingIntentList.add(pIntent);
    }

    /**
     * 브로드캐스팅 메시지를 받았을 때 처리할 수신자 정의
     *
     * 브로드캐스트 리시버란? : 안드로이드 4대 컴포넌트 중에 하나이다.
     * 말 그대로 방송 수신자이며 시스템이나 앱 등에서 이벤트 발생시 방송을 해주는 개념이다.
     * 이렇게 방송된 이벤트는 각 앱에서 필요한 방송 이벤트를 받아들이고 이벤트에 대한 처리를 리시버를 통해 할 수 있게 해준다.
     *
     * 어떠한 특정 작업이 벌어질 경우, 그것을 받고자 하는 곳에 알려주는 기능.
     */
    public class Alert_IntentReceiver extends BroadcastReceiver {
        private String mExpectedAction;
        private Intent mLastReceivedIntent;

        public Alert_IntentReceiver(String expectedAction) {
            mExpectedAction = expectedAction;
            mLastReceivedIntent = null;
        }

        public IntentFilter getFilter() {
            // 브로드캐스트의 액션을 등록하기 위한 인텐트 필터
            IntentFilter filter = new IntentFilter(mExpectedAction);
            return filter;
        }

        /**
         * 받았을 때 호출되는 메소드
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            // 인텐트 안에 뭔가 들어있으면
            if(intent != null) {
                // 최근에 받은 intent
                mLastReceivedIntent = intent;

                // alertRegister 메소드에서 PendingIntent를 붙이는 부분 즉,
                // mLocationManager.addProximityAlert(latitude, longitude, radius, expiration, intent); 에서의 intent
                int id = intent.getIntExtra("id", 0);

                if(id == 1001) {
                    Toast.makeText(getActivity().getApplicationContext(), "목적지 도착 200m 전 입니다!", Toast.LENGTH_LONG).show();
                    vibe.vibrate(2000); // 2초 동안 진동
                } else if(id == 1002) {
                    Toast.makeText(getActivity().getApplicationContext(), "목적지 도착 100m 전 입니다!", Toast.LENGTH_LONG).show();
                    vibe.vibrate(2000); // 2초 동안 진동
                } else if(id == 1003) {
                    Toast.makeText(getActivity().getApplicationContext(), "목적지 도착 50m 전 입니다!", Toast.LENGTH_LONG).show();
                    vibe.vibrate(2000); // 2초 동안 진동
                }
            }
        }

        public Intent getLastReceivedIntent() { return mLastReceivedIntent; }

        public void clearReceivedIntents() { mLastReceivedIntent = null; }
    }

    /**
     * 각 버튼 눌렸을 때 호출 될 함수들
     */
    private void stopOnClick() {
        Toast.makeText(getActivity().getApplicationContext(), "정지!", Toast.LENGTH_SHORT).show();
        mLocationManager.removeUpdates(mGPSListener);
    }

    private void startOnClick() {
        Toast.makeText(getActivity().getApplicationContext(), "시작! 잠시만 기다려주세요~", Toast.LENGTH_SHORT).show();
        startLocationService();
    }

    private void resetOnClick() {
        Toast.makeText(getActivity().getApplicationContext(), "초기화!", Toast.LENGTH_SHORT).show();

        // 새로이 경과시간, 최대속도, 누적이동거리를 체킹함.
        axisTime = 0;
        maxSpeed = 0;
        distance = 0;

        // 받아온 위치정보로 이동거리, 경과시간, 현재속도, 최대속도 계산해서 출력해 줄 것
        // 초기화를 위해 가져옴.
        TextView speedView = (TextView) getActivity().findViewById(R.id.display_speed);
        TextView distanceView = (TextView) getActivity().findViewById(R.id.display_distance);
        TextView timeView = (TextView) getActivity().findViewById(R.id.display_time);

        // 각 TextView 초기화
        speedView.setText("최대속도 : \n현재속도 : ");
        distanceView.setText("이동거리 : ");
        timeView.setText("경과시간 : ");

        map.clear(); // 지도상의 모든 마커 삭제
        if(checkIntent == true) { // 현재 목표지 설정이 된 상태라면
            mLocationManager.removeProximityAlert(pIntent); // 현재 설정되어 있는 목표지점 해제
            checkIntent = false; // 목표지 설정 해제 표시
        }
    }
}