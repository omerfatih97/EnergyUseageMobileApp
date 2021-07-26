package com.example.codebasev1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.codebasev1.data.DataFactory;
import com.example.codebasev1.data.Fatura;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NotifyService extends Service {

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    /*private List<Fatura> list;
    private boolean durum=false;
    Timer timer ;
    TimerTask timerTask ;
    String TAG = "Timers" ;
    int Your_X_SECS = 5 ;
    int i =0;
    Thread thread;
    @Override
    public IBinder onBind (Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand (Intent intent , int flags , int startId) {
        Log. e ( TAG , "onStartCommand" ) ;
        super .onStartCommand(intent , flags , startId) ;
        startTimer() ;
        return START_STICKY ;
    }
    @Override
    public void onCreate () {
        Log. e ( TAG , "onCreate" ) ;
        startTimer() ;
    }
    @Override
    public void onDestroy () {
        Log. e ( TAG , "onDestroy" ) ;
        stopTimerTask() ;
        super .onDestroy() ;
    }
    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler() ;
    public void startTimer () {
        timer = new Timer() ;
        initializeTimerTask() ;
        timer .schedule( timerTask , 5000 , Your_X_SECS * 1000 ) ; //
    }
    public void stopTimerTask () {
        if ( timer != null ) {
            timer .cancel() ;
            timer = null;
        }
    }
    public void initializeTimerTask () {
        timerTask = new TimerTask() {
            public void run () {
                handler .post( new Runnable() {
                    public void run () {
                        i++;
                        if(i==6)
                            durum=false;

                        if(!durum)
                            createNotification() ;
                    }
                }) ;
            }
        } ;
    }

    private void createNotification () {
        list= DataFactory.createSecondList();
        for (int i=0;i<list.size();i++){
            String temp=list.get(i).getDurum();
            if( temp.equals("Ödenmedi") || temp.equals("ÖDENDİ") ){

                NotificationManager mNotificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE ) ;
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext() , default_notification_channel_id ) ;
                mBuilder.setContentTitle( "Ödeme Uyarısı!" ) ;
                mBuilder.setContentText( list.get(i).getDonem()+" Dönemine Ait "+list.get(i).getTutar()+" TL Tutarında Borcunuz Bulunmaktadır." ) ;
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

            }
        }
        durum=true;
    }*/


    private static final int NOTIF_ID = 1;
    private static final String NOTIF_CHANNEL_ID = "Channel_Id";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        // do your jobs here

        startForeground();

        return super.onStartCommand(intent, flags, startId);
    }

    private void startForeground() {
        Intent notificationIntent = new Intent(this, MyFirebaseMessagingService.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Notification notification =new NotificationCompat.Builder(this,
                NOTIF_CHANNEL_ID) // don't forget create a notification channel first
                .setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("")
                .setContentIntent(pendingIntent)
                .build();
        startForeground(NOTIF_ID, notification);
    }
}
