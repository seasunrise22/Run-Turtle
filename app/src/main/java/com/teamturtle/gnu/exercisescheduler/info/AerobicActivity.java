package com.teamturtle.gnu.exercisescheduler.info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teamturtle.gnu.exercisescheduler.R;


public class AerobicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aerobic);

        findViewById(R.id.aerobic_shoulder_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Aerobic.class);
                        String aerobic_shoulder = "aerobic_shoulder";
                        intent_act.putExtra("data",aerobic_shoulder);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.aerobic_arm_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Aerobic.class);
                        String aerobic_arm = "aerobic_arm";
                        intent_act.putExtra("data",aerobic_arm);
                        startActivity(intent_act);
                    }
                }

        );


        findViewById(R.id.aerobic_back_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Aerobic.class);
                        String aerobic_back = "aerobic_back";
                        intent_act.putExtra("data",aerobic_back);
                        startActivity(intent_act);
                    }
                }

        );


        findViewById(R.id.aerobic_waist_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Aerobic.class);
                        String aerobic_waist = "aerobic_waist";
                        intent_act.putExtra("data",aerobic_waist);
                        startActivity(intent_act);
                    }
                }

        );


        findViewById(R.id.aerobic_stomach_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Aerobic.class);
                        String aerobic_stomach = "aerobic_stomach";
                        intent_act.putExtra("data",aerobic_stomach);
                        startActivity(intent_act);
                    }
                }

        );


        findViewById(R.id.aerobic_thigh_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Aerobic.class);
                        String aerobic_thigh = "aerobic_thigh";
                        intent_act.putExtra("data",aerobic_thigh);
                        startActivity(intent_act);
                    }
                }

        );


        findViewById(R.id.aerobic_hip_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Aerobic.class);
                        String aerobic_hip = "aerobic_hip";
                        intent_act.putExtra("data",aerobic_hip);
                        startActivity(intent_act);
                    }
                }

        );


        findViewById(R.id.aerobic_calf_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Aerobic.class);
                        String aerobic_calf = "aerobic_calf";
                        intent_act.putExtra("data",aerobic_calf);
                        startActivity(intent_act);
                    }
                }

        );





    }
}
