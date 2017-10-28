package com.example.sam.safedrinkingapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class AmIDrunkYetImproved extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_am_idrunk_yet_improved);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final GlobalVars gv = (GlobalVars) getApplicationContext();
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        final Date[] date = new Date[1];
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final TextView timeText = (TextView) findViewById(R.id.timeLeft);

        CountDownTimer timer = new CountDownTimer((long)gv.getTime()*3600000,1) {
            @Override
            public void onTick(long l) {
                if(l%3600000==0){
                    notifyMe();
                }
                date[0] = new Date(l);
                timeText.setText(formatter.format(l));
            }

            @Override
            public void onFinish() {

            }
        };
        date[0] = new Date(gv.getTime()*3600000);
        timeText.setText(formatter.format(date[0]));
        timer.start();
    }
    public void notifyMe(){
        final PendingIntent pending = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(),0);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder nb = new NotificationCompat.Builder(this);
        nb.setSmallIcon(R.drawable.ic_launcher_background);
        nb.setSound(sound);
        nb.setContentTitle("Drink Up!");
        nb.setContentText("You can drink now");
        nb.setContentIntent(pending);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0,nb.build());

    }

}
