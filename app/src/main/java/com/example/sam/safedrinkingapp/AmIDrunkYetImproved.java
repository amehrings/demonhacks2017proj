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
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.core.auth.AccessTokenManager;
import com.uber.sdk.android.core.auth.AuthenticationError;
import com.uber.sdk.android.core.auth.LoginCallback;
import com.uber.sdk.android.core.auth.LoginManager;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.android.rides.RideRequestButtonCallback;
import com.uber.sdk.core.auth.AccessToken;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.text.SimpleDateFormat;
import java.util.*;
import com.uber.sdk.rides.client.ServerTokenSession;
import com.uber.sdk.rides.client.error.ApiError;

public class AmIDrunkYetImproved extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_am_idrunk_yet_improved);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId("J9uWdtBnc9X0o7FAD7RpMhhiMIinK4aF")
                .setRedirectUri("http://localhost:8888/callback")
                .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                .setScopes(Arrays.asList(Scope.PROFILE, Scope.REQUEST))
                .build();

        UberSdk.initialize(config);


       /* SessionConfiguration config2 = new SessionConfiguration.Builder()
                // mandatory
                .setClientId("J9uWdtBnc9X0o7FAD7RpMhhiMIinK4aF")
                // required for enhanced button features
                .setServerToken("Y4zQSEmll_YLWOn1dFdgnJbbk893OFZMouNpRrTw")
                // required for implicit grant authentication
                .setRedirectUri("http://localhost:8888/callback")
                // required scope for Ride Request Widget features
                .setScopes(Arrays.asList(Scope.RIDE_WIDGETS))
                // optional: set sandbox as operating environment
                .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                .build();
        UberSdk.initialize(config2);*/

        /*RideRequestButton requestButton = new RideRequestButton(this);
        ConstraintLayout layout = findViewById(R.id.mylayout);
        layout.addView(requestButton);

        RideParameters rideParams = new RideParameters.Builder()
                .setPickupLocation(37.775304, -122.417522, "Uber HQ", "1455 Market Street, San Francisco")
                .setDropoffLocation(37.795079, -122.4397805, "Embarcadero", "One Embarcadero Center, San Francisco") // Price estimate will only be provided if this is provided.
                .setProductId("a1111c8c-c720-46c3-8534-2fcdd730040d") // Optional. If not provided, the cheapest product will be used.
                .build();

        ServerTokenSession session = new ServerTokenSession(config2);

        RideRequestButtonCallback callback = new RideRequestButtonCallback() {

            @Override
            public void onRideInformationLoaded() {

            }

            @Override
            public void onError(ApiError apiError) {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        };

        requestButton.setRideParameters(rideParams);
        requestButton.setSession(session);
        requestButton.setCallback(callback);
        requestButton.loadRideInformation();*/

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
