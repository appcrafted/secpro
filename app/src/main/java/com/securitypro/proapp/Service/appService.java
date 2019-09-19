package com.securitypro.proapp.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.securitypro.proapp.Activity.MainActivity;
import com.securitypro.proapp.R;


public class appService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // do your jobs here
        String action = intent.getAction();
        try {
            if ("WiFis".equals(action)){
                mNotification("WiFi has been used");

            }else  if ("bluetooth".equals(action)){
                mNotification("BlueTooth has been used");

            }else  if ("micnotification".equals(action)){
                mNotification("Microphone has been used");

            }else  if ("camnotification".equals(action)){
                mNotification("Camera has been used");
            }else {
                mNotification("App is running");
            }
        }catch (Exception e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }

        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d(getClass().getName(), "App just got removed from Recents!");

    }

    public void mNotification(String msg) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
        builder.setContentTitle("AntiSpy");
        builder.setContentText(msg);
        builder.setVibrate(new long[] { 1000, 1000});
        Uri sound = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/raw/bell");
        builder.setSound(sound);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build());
    }
}
