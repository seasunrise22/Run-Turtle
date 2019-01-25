package com.teamturtle.gnu.exercisescheduler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.teamturtle.gnu.exercisescheduler.settingTap.LPasswordActivity;

//import com.teamturtle.gnu.exercisescheduler.settingTap.LPasswordActivity;

public class IntroActivity extends AppCompatActivity {

    // 일정시간 후 MainActivity로 화면을 전환시키기 위한 Handler 변수 선언
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Handler 객체 생성
        handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                //비번 걸려면 밑에 주석 풀고 밑에 코드랑 바꾸기
                Intent intent = new Intent(getApplicationContext(), LPasswordActivity.class);
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500); // 1.5초 후 run() 메소드의 내용을 실행하여 MainActivity로 화면을 전환함
    }
}
