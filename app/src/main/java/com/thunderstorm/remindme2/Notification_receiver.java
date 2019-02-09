package com.thunderstorm.remindme2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class Notification_receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String reminder = intent.getStringExtra("Title");
        int id = intent.getIntExtra("ID", 0);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel("default", "channel1", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("channel1");
            channel.enableLights(true);           //Added to to get the LED to fire
            channel.setLightColor(Color.BLUE);    //Added to to get the LED to fire
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default")
                .setDefaults(0)  //Added to to get the LED to fire
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setContentTitle("Reminding You To:")
                .setContentText(reminder)
                .setLights(Color.BLUE, 3000, 3000)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setSound(sound);

        if (notificationManager != null) {
            notificationManager.notify( id ,builder.build());
        }
    }
}
