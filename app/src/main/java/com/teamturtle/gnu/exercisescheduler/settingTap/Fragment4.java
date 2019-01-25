package com.teamturtle.gnu.exercisescheduler.settingTap;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.teamturtle.gnu.exercisescheduler.R;

public class Fragment4 extends Fragment {

    ListView listView1;
    ToggleButton toggleButton;
    static int positionSet;

    IconTextListAdapter adapter;

    private void resetAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Intent Intent = new Intent(getActivity(), BroadcastD.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, Intent, 0);
        alarmManager.cancel(pIntent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment4, container, false);

        listView1 = (ListView) rootView.findViewById(R.id.LV1);

        adapter = new IconTextListAdapter(getActivity());

        toggleButton = (ToggleButton) rootView.findViewById(R.id.tbAlarm);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SWITCH", Context.MODE_PRIVATE);
        toggleButton.setChecked(sharedPreferences.getBoolean("SWITCHOFONOROFF", true));

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("SWITCH", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("SWITCHOFONOROFF", true);
                    editor.commit();


                }else{
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("SWITCH", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("SWITCHOFONOROFF", false);
                    editor.commit();

                    resetAlarm(getActivity());
                }

            }
        });

        String[] temp = new String[1];

        temp[0] = "비밀번호 설정";

        Resources res = getResources();

        adapter.addItem(new IconText(res.getDrawable(R.drawable.popup_image), temp));
        adapter.addItem(new IconText(res.getDrawable(R.drawable.alert_image), "운동 시작 알림"));
        adapter.addItem(new IconText(res.getDrawable(R.drawable.switch_image), "알람 On / Off"));

        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                IconText curItem = (IconText)adapter.getItem(position);
                String[] curData = curItem.getData();

                Toast.makeText(getActivity().getApplicationContext(), curData[0],Toast.LENGTH_LONG).show();

                if(position == 0) {
                    Intent intent = new Intent(getActivity(), Rpassword.class);
                    startActivity(intent);
                }

                else if(position == 1 && toggleButton.isChecked()) {
                    Intent intent = new Intent(getActivity(), AlarmActivity.class);
                    startActivity(intent);
                }

            }
        });

        return rootView;
    }
}