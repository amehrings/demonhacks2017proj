package com.example.sam.safedrinkingapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Sam on 10/28/2017.
 */

public class TimerUberFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.content_am_idrunk_yet_improved,container,false);

        return v;
    }
    public void onStart(){
        super.onStart();
        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId("J9uWdtBnc9X0o7FAD7RpMhhiMIinK4aF")
                .setRedirectUri("http://localhost:8888/callback")
                .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                .setScopes(Arrays.asList(Scope.PROFILE, Scope.REQUEST))
                .build();

        UberSdk.initialize(config);
        final GlobalVars gv = (GlobalVars) getActivity().getApplicationContext();
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        final Date[] date = new Date[1];
        final TextView timeText = getView().findViewById(R.id.timeLeft);

        CountDownTimer timer = new CountDownTimer((long)gv.getTime()*3600000,1) {
            @Override
            public void onTick(long l) {
                if(l%360000==0){
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
        final PendingIntent pending = PendingIntent.getActivity(getActivity().getApplicationContext(), 0, new Intent(),0);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder nb = new NotificationCompat.Builder(getActivity().getBaseContext());
        nb.setSmallIcon(R.drawable.ic_launcher_background);
        nb.setSound(sound);
        nb.setContentTitle("Drink Up!");
        nb.setContentText("You can drink now");
        nb.setContentIntent(pending);
        NotificationManager nm = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0,nb.build());

    }
}
