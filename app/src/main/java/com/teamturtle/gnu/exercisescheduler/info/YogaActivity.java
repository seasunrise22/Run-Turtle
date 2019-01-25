package com.teamturtle.gnu.exercisescheduler.info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teamturtle.gnu.exercisescheduler.R;

public class YogaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yoga);


        findViewById(R.id.yoga_shoulder_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Yoga.class);
                        String yoga_shoulder = "yoga_shoulder";
                        intent_act.putExtra("data",yoga_shoulder);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.yoga_arm_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Yoga.class);
                        String yoga_arm = "yoga_arm";
                        intent_act.putExtra("data",yoga_arm);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.yoga_back_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Yoga.class);
                        String yoga_back = "yoga_back";
                        intent_act.putExtra("data",yoga_back);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.yoga_waist_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Yoga.class);
                        String yoga_waist = "yoga_waist";
                        intent_act.putExtra("data",yoga_waist);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.yoga_stomach_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Yoga.class);
                        String yoga_stomach = "yoga_stomach";
                        intent_act.putExtra("data",yoga_stomach);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.yoga_thigh_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Yoga.class);
                        String yoga_thigh = "yoga_thigh";
                        intent_act.putExtra("data",yoga_thigh);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.yoga_hip_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Yoga.class);
                        String yoga_hip = "yoga_hip";
                        intent_act.putExtra("data",yoga_hip);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.yoga_calf_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Yoga.class);
                        String yoga_calf = "yoga_calf";
                        intent_act.putExtra("data",yoga_calf);
                        startActivity(intent_act);
                    }
                }

        );

    }
}

