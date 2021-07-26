package com.example.codebasev1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("newToken", s);
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("fb", s).apply();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) { // Data mesajı içeriyor mu
            //Uygulama arkaplanda veya ön planda olması farketmez. Her zaman çağırılacaktır.
            //Gelen içerik json formatındadır.
            Log.d("Mesaj", "Mesaj data içeriği: " + remoteMessage.getData());

            //Json formatındaki datayı parse edip kullanabiliriz. Biz direk datayı Push Notification olarak bastırıyoruz


        }

        if (remoteMessage.getNotification() != null) { //Notification mesajı içeriyor mu
            //Uygulama arkaplanda ise burası çağrılmaz.Ön planda ise notification mesajı geldiğinde çağırılır
            //getBody() ile mesaj içeriği
            //getTitle() ile mesaj başlığı
            Log.d("Mesaj:", "Mesaj Notification Başlığı: " + remoteMessage.getNotification().getTitle() +" "+"Mesaj Notification İçeriği: " + remoteMessage.getNotification().getBody() );

            //Gelen içeriğe göre notifikasyon bildiriminde bulunma
            sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(String messageTitle,String messageBody) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        long[] pattern = {500,500,500,500};//Titreşim ayarı

        /*NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setVibrate(pattern)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);*/

        try {
            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + this.getPackageName() + "/raw/notification");
            Ringtone r = RingtoneManager.getRingtone(this, alarmSound);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

        NotificationManager mNotificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE ) ;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext() , default_notification_channel_id ) ;
        mBuilder.setContentTitle( messageTitle) ;
        mBuilder.setContentText(messageBody) ;
        mBuilder.setTicker( "Notification Listener Service Example" ) ;
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setLargeIcon(BitmapFactory.decodeResource(getBaseContext().getResources(),
                        R.mipmap.ic_launcher_foreground));
        mBuilder.setAutoCancel( true ) ;
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
            mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel) ;
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(( int ) System. currentTimeMillis () , mBuilder.build()) ;

        //notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fb", "empty");
    }
}
