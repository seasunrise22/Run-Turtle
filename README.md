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

- 기능1 : GPS 기능을 이용한 이동거리 측정과 기록, 목적지 알람 기능
- 기능2 : 스케줄러 기능
- 기능3 : 체크리스트, 수행 성취도 평가 기능 
- 기능4 : 칼로리 계산기, 운동 정보 기능
- 기능5 : 운동 시작 시간 예약 및 알람, 비밀번호 설정 기능

저는 기능1과 프로그램 통합을 담당했습니다.

## Development Environment
- IDE : Android Studio
- Language : Java, php
- Server : Apache Tomcat 
- DB : MySQL, phpMyAdmin

## Code Preview
***GPS로 현재 위치 측정하기***
<pre><code>
// LocationManager 호출
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
  } catch(SecurityException ex) {
    ex.printStackTrace();
  }
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
