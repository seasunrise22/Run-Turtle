package com.teamturtle.gnu.exercisescheduler.gps;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.teamturtle.gnu.exercisescheduler.MainActivity;
import com.teamturtle.gnu.exercisescheduler.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.teamturtle.gnu.exercisescheduler.MainActivity.mContext;
import static com.teamturtle.gnu.exercisescheduler.R.id.btn_finishAddress;
import static com.teamturtle.gnu.exercisescheduler.R.id.btn_retrieveAddress;
import static com.teamturtle.gnu.exercisescheduler.R.id.btn_showAddress;

public class Fragment1_Address extends Activity {

    final String TAG = "Fragment1_Address.class";

    Geocoder mGeocoder;
    EditText inputAddr; // 주소 입력 받는 부분
    TextView resultAddr; // 주소 검색 결과 뜨는 부분

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 팝업창 타이틀바 없앰
        setContentView(R.layout.fragment1_address);

        inputAddr = (EditText) findViewById(R.id.input_Address);
        resultAddr = (TextView) findViewById(R.id.result_Address);

        // 주소 검색 버튼 이벤트 처리
        Button showAddr = (Button) findViewById(btn_showAddress);
        showAddr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 사용자가 입력한 주소 정보 확인
                String searchStr = inputAddr.getText().toString();
                // 주소 정보를 이용해 위치 좌표 찾기 메소드 호출
                searchLocation(searchStr);
            }
        });

        // 검색 기록 버튼 이벤트 처리
        Button retrieveAddr = (Button) findViewById(btn_retrieveAddress);
        retrieveAddr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 자꾸 터져서 핸들러 사용.
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Fragment1_Address.this, RetrieveAddrActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 200);
            }
        });

        // 검색 기록 액티비티에서 넘어온 주소 문자열 처리.
        Intent intent = getIntent(); // RetrieveAddrActivty 에서 넘겨받은 intent
        String getAddr = intent.getStringExtra("addrName"); // 주소 뽑아냄
        inputAddr.setText(getAddr);

        // 지오코더 객체 생성
        mGeocoder = new Geocoder(this, Locale.KOREAN);
    }

    /**
     * 주소를 이용해 위치 좌표를 찾는 메소드 정의
     */
    private void searchLocation(String searchStr) {
        List<Address> addressList = null; // 결과값이 들어갈 리스트 선언
        try {
            addressList = mGeocoder.getFromLocationName(searchStr, 3); // 동명주소 3개

            // 검색 된 주소의 위도와 경도값을 각 변수에 할당.
            // 이걸 이제 구글맵이 있는 액티비티로 넘겨줄 것.
            Address firstAddr = addressList.get(0);
            final Double addrLatitude = firstAddr.getLatitude();
            final Double addrLongitude = firstAddr.getLongitude();

            if (addressList != null) {
                resultAddr.append(addressList.size() + " 개 주소 검색 결과");

                for (int i = 0; i < addressList.size(); i++) {
                    Address outAddr = addressList.get(i);
                    int addrCount = outAddr.getMaxAddressLineIndex() + 1; // 동명주소로 인해 list안에 몇 개 들어가있나.
                    StringBuffer outAddrStr = new StringBuffer(); // String과 다르게 변경이 가능하다.
                    for (int k = 0; k < addrCount; k++) {
                        outAddrStr.append(outAddr.getAddressLine(k));
                    }

                    resultAddr.append("\n\t주소 #" + i + " : " + outAddrStr.toString());
                }
            }

            //******************* 데이터베이스 관련 시작 *********************************//
            // AddressRequest 클래스의 객체에 입력받은 주소 문자열을 리스너와 함께 넘김.
            Response.Listener<String> responseListener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if(success) {
                            Log.d(TAG, "success!!");
                            Toast.makeText(getApplicationContext(), "주소 검색 완료", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            AddressRequest addressRequest = new AddressRequest(searchStr, responseListener);
            RequestQueue queue = Volley.newRequestQueue(Fragment1_Address.this);
            queue.add(addressRequest);
            //******************* 데이터베이스 관련 끝 *********************************//

            // 목적지 설정 완료 버튼 이벤트 처리
            Button finishAddr = (Button) findViewById(btn_finishAddress);
            finishAddr.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    // 구글맵이 있는 Fragment1 클래스의 setUpGoal 메소드에 검색된 목적지의 위도와 경도값 전달.
                    // MainActivity의 goToFragment1_setUpGoal 메소드를 경유한다.
                    ((MainActivity) mContext).goToFragment1_setUpGoal(addrLatitude, addrLongitude);

                    Toast mToast = Toast.makeText(getApplicationContext(), "목적지가 설정되었습니다.", Toast.LENGTH_LONG);
                    mToast.show();

                    // 목적지 설정 완료했으니 목적지 설정 액티비티 종료
                    finish();
                }
            });
        } catch (IOException ex) {
            Toast mToast = Toast.makeText(getApplicationContext(), "searchLocation 내부 예외 발생!", Toast.LENGTH_LONG);
            mToast.show();
        }
    }
}

