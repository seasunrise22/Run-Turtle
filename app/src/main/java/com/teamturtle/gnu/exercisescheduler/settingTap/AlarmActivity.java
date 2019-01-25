package com.teamturtle.gnu.exercisescheduler.settingTap;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TimePicker;

import java.util.Calendar;

public class AlarmActivity extends Activity{

    private Calendar mCalendar = Calendar.getInstance();

    private int sHour;
    private int sMinute;
    private int switchvalue;
    public int position;
    private NotificationManager mNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_alarm);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

            sHour = mCalendar.get(Calendar.HOUR_OF_DAY);
            sMinute = mCalendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(AlarmActivity.this, timeSetListener, sHour, sMinute, false);

            timePickerDialog.show();
    }

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stub

            sHour = hourOfDay;
            sMinute = minute;

            /*String msg = String.format("%d : %d", hourOfDay, minute);

            Toast.makeText(AlarmActivity.this, msg, Toast.LENGTH_SHORT).show();*/

            mNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(AlarmActivity.this, BroadcastD.class);
            PendingIntent sender = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, 0);

            mCalendar.set(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DATE), sHour, sMinute, 0);

            am.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, sender);
        }

    };

}