package com.teamturtle.gnu.exercisescheduler;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.teamturtle.gnu.exercisescheduler.gps.Fragment1;
import com.teamturtle.gnu.exercisescheduler.info.Fragment3;
import com.teamturtle.gnu.exercisescheduler.scheduler.Fragment2;
import com.teamturtle.gnu.exercisescheduler.settingTap.Fragment4;

public class MainActivity extends AppCompatActivity {

    public static Context mContext;

    Toolbar toolbar;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;

    // fragment1 태그
    private static final String TAG_fragment1 = "Fragment1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        // 권한 얻기
        checkDangerousPermissions();

        // xml layout파일에 AppBarLayout영역 안에 있던 Toolbar를 여기서 잡아다 쓰나보다.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // https://developer.android.com/reference/android/support/v7/app/AppCompatActivity.html#setSupportActionBar(android.support.v7.widget.Toolbar)
        // Set a Toolbar to act as the ActionBar for this Activity window.
        // XML레이아웃에서 정의한 Toolbar객체는 코드에서 setSupportActionBar() 메소드를 이용해 액션바로 설정한다.
        // 이 메소드는 액티비티에 디폴트로 만들어진 액션바가 없을 경우에만 동작하므로 styles.xml파일의 액티비티 스타일을 액션바가 없는 스타일로 변경해야만 한다.
        // NoActionBar 스타일로 바꾸면 이 스타일을 적용한 액티비티에는 액션바가 만들어지지 않으므로 코드에서 setSupportActionBar() 메소드를 호출하여 직접 액션바를 설정할 수 있다.
        setSupportActionBar(toolbar);

        // https://developer.android.com/reference/android/support/v7/app/AppCompatActivity.html#getSupportActionBar()
        // Retrieve a reference to this activity's ActionBar.
        ActionBar actionBar = getSupportActionBar();
        // Set whether an activity title/subtitle should be displayed.
        actionBar.setDisplayShowTitleEnabled(false);

        // 각 프래그먼트 객체 생성
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();

        // FragmentManager를 이용해서 activity_main안에 있는 FrameLayout 부분에 각 fragment 파일을 replace 해주는 군.
        // 여기서는 일단 기본으로 1번 fragment를 먼저 보여줌.
        getFragmentManager().beginTransaction().replace(R.id.container, fragment1, TAG_fragment1).commit();

        // 여기서 R.id.tabs는 activity_main.xml 파일에서 tabs라고 명명한 그 Layout
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("위치알림"));
        tabs.addTab(tabs.newTab().setText("스케줄러"));
        tabs.addTab(tabs.newTab().setText("운동정보"));
        tabs.addTab(tabs.newTab().setText("환경설정"));

        tabs.setBackgroundColor(Color.parseColor("#ffffff"));
        // 탭 선택시의 반응들. 리스너를 이용하여 탭 선택 시 다른 fragment가 보이도록 설정.
        // setOnTabSelectedListener는 이제 쓰지말도록 권장.
        // 대신 addOnTabSelectedListener를 쓰란다.
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭: " + position);

                Fragment selected = null;
                if(position == 0) {
                    selected = fragment1;
                } else if(position == 1) {
                    selected = fragment2;
                } else if(position == 2) {
                    selected = fragment3;
                } else if(position == 3) {
                    selected = fragment4;
                }

                // 선택된 fragment로 교체한다.
                // selected 변수는 Fragment를 담기 위한 그릇.
                getFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * Fragment1 의 setUpGoal 메소드를 쓰기 위한 징검다리 메소드
     */
    public void goToFragment1_setUpGoal(Double latitude, Double longitude) {

        Fragment1 mFragment1 = (Fragment1) getFragmentManager().findFragmentByTag(TAG_fragment1);
        mFragment1.setUpGoal(latitude, longitude);
    }

    /**
     * 권한검사
     */
    protected void checkDangerousPermissions()
    {
        // 얻고자 하는 권한 추가
        String[] permissions = {
                android.Manifest.permission.ACCESS_FINE_LOCATION
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++)
        {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED)
            {
                // 권한 없음
                //Toast.makeText(this, permissions[i] + "권한 없음", Toast.LENGTH_LONG).show();

                // 이 권한이 필요한 이유를 설명해야 하는가? - 맨 처음 권한 요청하는 경우 false를 리턴함.
                // dialog 같은것을 띄워서 사용자에게 해당 권한이 필요한 이유에 대해 설명.
                // 해당 설명이 끝난뒤 requestPermissions() 함수를 호출하여 권한허가를 요청해야함.
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0]))
                {
                    //Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();

                } else {

                    // 권한 설명 필요 없는 경우
                    // requestPermissions 함수를 사용해서 사용자에게 권한허가를 요청하는 다이어로그를 띄울수 있다.
                    // 필요한 권한과 요청 코드를 넣어서 권한허가요청에 대한 결과를 받아야 함.
                    ActivityCompat.requestPermissions(this, permissions, 1);
                }
            } else {

                // 권한 있음
                //Toast.makeText(this, permissions[i] + "권한 있음", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 권한허가 요청 후 결과 받아오기
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch(requestCode)
        {
            // case 1은 GPS
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // 권한 허가
                    // 해당 권한을 사용해서 작업을 진행할 수 있다.
                    //Toast.makeText(this, permissions[0] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {

                    // 권한 거부
                    // 사용자가 해당권한을 거부했을때 해주어야 할 동작을 수행.
                    //Toast.makeText(this, permissions[0] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }

                return;
        }
    }
}


