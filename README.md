# Run-Turtle
- 개발인원 : 5명
- 역할
  - GPS 기능을 이용한 이동거리 측정과 기록, 목적지 알람 기능 개발
  - 프로그램 통합
  - 팀장
## Introduction
대학교 4학년 프로젝트 과제로 만들었던 종합운동관리 안드로이드 앱입니다.

다섯 명의 팀원이 각자 하나씩의 기능들을 맡아 구현한 후, 탭으로 구분되어지는 틀 안으로 통합해 하나의 안드로이드 애플리케이션으로 완성시켰습니다.

각자 맡은 기능들은 아래와 같습니다.

- 기능 1 : GPS를 활용한 위치기록, 이동거리 측정, Geocoder를 활용한 목적지 설정(주소 검색) 및 목적지 접근 알람 기능
- 기능 2 : 스케줄러 기능
- 기능 3 : 체크리스트, 수행 성취도 평가 기능 
- 기능 4 : 칼로리 계산기, 운동 정보 기능
- 기능 5 : 운동 시작 시간 예약 및 알람, 비밀번호 설정 기능

저는 기능1과 프로그램 통합을 담당했습니다.

## Development Environment
- IDE : Android Studio
- Language : Java, php
- Server : Apache Tomcat 
- DB : MySQL, phpMyAdmin

## Code Preview
***GPS를 이용한 현재 위치 측정***
<pre><code>// LocationManager 호출
private void startLocationService() {
  mGPSListener = new GPSListener(); // 리스너 객체 생성
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
  }
...

// 위치 관리자가 쏜 현재 위치를 받을 GPS 리스너(LocationListener)
private class GPSListener implements LocationListener {
  ... 
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
      ...
</code></pre>

***속도, 이동거리, 이동시간 측정***
<pre><code>// 현재속도, 최대속도 구하기
if(location != null) {
  currentSpeed = location.getSpeed();
  if (currentSpeed > maxSpeed) {
      maxSpeed = currentSpeed;
  }
  ...
}

// 누적 이동거리 구하기
if(lastLocation == null) {
   lastLocation = location; // 최근 위치를 lastLocation 변수에 저장
  } else {
    // distanceTo = Returns the approximate distance in meters between this location and the given location.
    distance = distance + lastLocation.distanceTo(location); 
    lastLocation = location; // 새롭게 갱신 된 최근 위치를 다시 lastLocation 변수에 저장
    ...
    }

// 이동한 시간 구하기
if(axisTime == 0) {
    currentTime = System.currentTimeMillis();
    axisTime = currentTime; // 기준시간을 현재시간으로 설정
    } else {
      currentTime = System.currentTimeMillis();
      between = (currentTime - axisTime) / 1000; // 1000밀리세컨드 = 1초. 즉, 1000으로 나누어주면 초가 됨.
      ...
  }
</code></pre>

***Geocoder를 이용한 목적지 검색***
<code><pre>
private void searchLocation(String searchStr) {
  List<Address> addressList = null; // 결과값이 들어갈 리스트 선언
  try {
      addressList = mGeocoder.getFromLocationName(searchStr, 3); // 동명주소 3개
      Address firstAddr = addressList.get(0);
      final Double addrLatitude = firstAddr.getLatitude();
      final Double addrLongitude = firstAddr.getLongitude();
      ...
    }
</code></pre>
  
***php를 매개로 MySQL과 JSON 형식으로 통신***
<code><pre>
Response.Listener<String> responseListener = new Response.Listener<String>() {

@Override
public void onResponse(String response) {
      try {
        JSONObject jsonResponse = new JSONObject(response);
        boolean success = jsonResponse.getBoolean("success");
        if(success) {
        ...
        }
        
AddressRequest addressRequest = new AddressRequest(searchStr, responseListener);
</code></pre>

<code><pre>
public class AddressRequest extends StringRequest {
    private static final String ADDRESS_REQUEST_URL = "http://exercisemanagementapp.iptime.org/address.php";
    private Map<String, String> params;    
    public AddressRequest(String addrName, Response.Listener<String> listener) {
        super(Method.POST, ADDRESS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("addrName", addrName);
    }
    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}
</code></pre>

## Screenshots
***기능1***

![resize_gps_01](https://user-images.githubusercontent.com/45503931/56084470-5fe88c00-5e6e-11e9-82a9-72c0155519ac.png)
![resize_gps_02](https://user-images.githubusercontent.com/45503931/56084471-5fe88c00-5e6e-11e9-9c84-e86e15abcca6.png)
---
***기능2***

![resize_sche_01](https://user-images.githubusercontent.com/45503931/56084474-60812280-5e6e-11e9-93a1-c879df3d7897.png)
---
***기능3***

![resize_check_01](https://user-images.githubusercontent.com/45503931/56084497-a211cd80-5e6e-11e9-8f05-8a712bdcf8ae.png)
---
***기능4***

![resize_info_01](https://user-images.githubusercontent.com/45503931/56084472-5fe88c00-5e6e-11e9-94e0-a9474d6d407c.png)
![resize_info_02](https://user-images.githubusercontent.com/45503931/56084473-60812280-5e6e-11e9-98ee-e8b0a4854db3.png)
---
***기능5***

![resize_set_01](https://user-images.githubusercontent.com/45503931/56084475-60812280-5e6e-11e9-8245-3c1dd48e8623.png)
![resize_set_02](https://user-images.githubusercontent.com/45503931/56084477-60812280-5e6e-11e9-9746-6dcf75693b80.png)
