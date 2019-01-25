package com.teamturtle.gnu.exercisescheduler.info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teamturtle.gnu.exercisescheduler.R;

public class MuscularActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.muscular_strength);

        findViewById(R.id.muscular_shoulder_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Muscular.class);
                        String muscular_shoulder = "muscular_shoulder";
                        intent_act.putExtra("data",muscular_shoulder);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.muscular_arm_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Muscular.class);
                        String muscular_arm = "muscular_arm";
                        intent_act.putExtra("data",muscular_arm);
                        startActivity(intent_act);
                    }
                }

        );
        findViewById(R.id.muscular_back_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Muscular.class);
                        String muscular_back = "muscular_back";
                        intent_act.putExtra("data",muscular_back);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.muscular_waist_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Muscular.class);
                        String muscular_waist= "muscular_waist";
                        intent_act.putExtra("data",muscular_waist);
                        startActivity(intent_act);
                    }
                }

        );


        findViewById(R.id.muscular_stomach_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Muscular.class);
                        String muscular_stomach = "muscular_stomach";
                        intent_act.putExtra("data",muscular_stomach);
                        startActivity(intent_act);
                    }
                }

        );
        findViewById(R.id.muscular_thigh_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Muscular.class);
                        String muscular_thigh = "muscular_thigh";
                        intent_act.putExtra("data",muscular_thigh);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.muscular_hip_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Muscular.class);
                        String muscular_hip = "muscular_hip";
                        intent_act.putExtra("data",muscular_hip);
                        startActivity(intent_act);
                    }
                }

        );

        findViewById(R.id.muscular_calf_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),Youtube_Muscular.class);
                        String muscular_calf = "muscular_calf";
                        intent_act.putExtra("data",muscular_calf);
                        startActivity(intent_act);
                    }
                }

        );
    }
}
