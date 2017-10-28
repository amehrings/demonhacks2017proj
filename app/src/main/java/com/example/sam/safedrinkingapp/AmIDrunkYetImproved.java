package com.example.sam.safedrinkingapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AmIDrunkYetImproved extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        final Notification notify = new Notification(android.R.drawable.stat_notify_more,"You can drink again now",System.currentTimeMillis());
        final PendingIntent pending = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(),0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_am_idrunk_yet_improved);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final GlobalVars gv = (GlobalVars) getApplicationContext();
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
                if(l%360000==0){
                    //notify.setLatestEventInfo();
                    nm.notify(0, notify);
                }
                timeText.setText(String.valueOf(l));
            }

            @Override
            public void onFinish() {

            }
        };
        timeText.setText(String.valueOf(gv.getTime()*3600000));
        timer.start();
    }

}
