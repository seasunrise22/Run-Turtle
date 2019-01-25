package com.teamturtle.gnu.exercisescheduler.info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teamturtle.gnu.exercisescheduler.R;

public class StretchingActivity extends AppCompatActivity {//유투브 바꾸기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stretching);

        findViewById(R.id.stretching_shoulder_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Stretching.class);
                        String stretching_shoulder = "stretching_shoulder";
                        intent_act.putExtra("data",stretching_shoulder);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.stretching_arm_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Stretching.class);
                        String stretching_arm = "stretching_arm";
                        intent_act.putExtra("data",stretching_arm);
                        startActivity(intent_act);
                    }
                }

        );
        findViewById(R.id.stretching_back_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Stretching.class);
                        String stretching_back = "stretching_back";
                        intent_act.putExtra("data",stretching_back);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.stretching_waist_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Stretching.class);
                        String stretching_waist= "stretching_waist";
                        intent_act.putExtra("data",stretching_waist);
                        startActivity(intent_act);
                    }
                }

        );


        findViewById(R.id.stretching_stomach_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Stretching.class);
                        String stretching_stomach = "stretching_stomach";
                        intent_act.putExtra("data",stretching_stomach);
                        startActivity(intent_act);
                    }
                }

        );
        findViewById(R.id.stretching_thigh_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Stretching.class);
                        String stretching_thigh = "stretching_thigh";
                        intent_act.putExtra("data",stretching_thigh);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.stretching_hip_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Stretching.class);
                        String stretching_hip = "stretching_hip";
                        intent_act.putExtra("data",stretching_hip);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.stretching_calf_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Stretching.class);
                        String stretching_calf = "stretching_calf";
                        intent_act.putExtra("data",stretching_calf);
                        startActivity(intent_act);
                    }
                }

        );
    }
}