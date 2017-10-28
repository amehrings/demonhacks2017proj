package com.example.sam.safedrinkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class BAC extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       startAIDY();
                                   }
                               });

            TextView textView6 = (TextView)findViewById(R.id.textView6);
        textView6.setText(String.format("%.2f", MainActivity.drunkCalc));

            TextView textView7 = (TextView)findViewById(R.id.textView7);
        textView7.setText("Hard Liquor (Shot): " + String.format("%.2f", MainActivity.hardLiquorShot));

            TextView textView8 = (TextView)findViewById(R.id.textView8);
        textView8.setText("Beer (12oz. @ 5%): " + String.format("%.2f", MainActivity.beer));

            TextView textView9 = (TextView)findViewById(R.id.textView9);
        textView9.setText("Wine Bottle (25 oz. @ 12%): " + String.format("%.2f",MainActivity.wine));

        }

    public void startAIDY() {
        Intent intent;
        intent = new Intent(this, AmIDrunkYet.class);
        startActivity(intent);
    }

}
