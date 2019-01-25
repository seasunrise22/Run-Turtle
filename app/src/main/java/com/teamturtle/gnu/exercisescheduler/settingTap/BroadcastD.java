package com.teamturtle.gnu.exercisescheduler.settingTap;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.teamturtle.gnu.exercisescheduler.MainActivity;
import com.teamturtle.gnu.exercisescheduler.R;

/**
 * Created by chris on 2017-05-09.
 */

public class BroadcastD extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String INTENT_ACTION = Intent.ACTION_BOOT_COMPLETED;

            //NotificationManager 안드로이드 상태바에 메세지를 던지기위한 서비스 불러오고
            NotificationManager notificationmanager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, LPasswordActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
            Notification.Builder builder = new Notification.Builder(context);
            builder.setSmallIcon(R.drawable.alert_image).setTicker("운동할 시간입니다!").setWhen(System.currentTimeMillis())
                    .setNumber(1).setContentTitle("달려라 거북이").setContentText("운동할 시간입니다!")
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingIntent).setAutoCancel(true);

            notificationmanager.notify(1, builder.build());

    }
}
