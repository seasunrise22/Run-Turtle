package com.teamturtle.gnu.exercisescheduler.info;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.teamturtle.gnu.exercisescheduler.R;

public class Fragment3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.main, container, false);

        rootView.findViewById(R.id.button_aerobic).setOnClickListener( //유산소 버튼 클릭
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getActivity(), AerobicActivity.class);
                        startActivity(intent_act);
                    }
                }
        );
        rootView.findViewById(R.id.button_muscular).setOnClickListener( //근력 버튼 클릭
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getActivity(), MuscularActivity.class);
                        startActivity(intent_act);
                    }
                }
        );
        rootView.findViewById(R.id.button_stretching).setOnClickListener( //스트레칭 버튼 클릭
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getActivity(),StretchingActivity.class);
                        startActivity(intent_act);
                    }
                }
        );
        rootView.findViewById(R.id.button_yoga).setOnClickListener( //요가 버튼 클릭
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getActivity(),YogaActivity.class);
                        startActivity(intent_act);
                    }
                }
        );
        rootView.findViewById(R.id.button_clorie).setOnClickListener( //칼로리계산기 버튼클릭
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getActivity(),CalorieActivity.class);
                        startActivity(intent_act);
                    }
                }
        );
        return rootView;
    }
}