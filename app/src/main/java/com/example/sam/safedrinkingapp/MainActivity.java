package com.example.sam.safedrinkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {
    public static double drunkCalc = 0;
    public static double hardLiquorShot = 0;
    public static double beer = 0;
    public static double wine = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                final GlobalVars gv = (GlobalVars) getApplicationContext();
                EditText weightText = (EditText) findViewById(R.id.Weight);
                gv.setWeight(Integer.valueOf(String.valueOf(weightText.getText())));
                NumberPicker timePick = (NumberPicker) findViewById(R.id.numberPicker);
                gv.setTime(timePick.getValue());
                RadioGroup sexgroup = (RadioGroup)findViewById(R.id.Sex);
                int sexid = sexgroup.getCheckedRadioButtonId();
                RadioButton sex = (RadioButton)findViewById(sexid);
                gv.setSex((String)sex.getText());
                if(gv.getSex().equals("Male")) {
                    drunkCalc = (((.08 + (.015 *gv.getTime() )) * (gv.getWeight() * gv.getMaleConst())) / 5.14);
                } else {
                    drunkCalc = (((.08 + (.015 *gv.getTime() )) * (gv.getWeight() * gv.getFemaleConst())) / 5.14);
                }


                hardLiquorShot = drunkCalc/(.40*1.25);
                beer = drunkCalc/(.05*12);
                wine = drunkCalc/(.12*25.4);


                startBAC();
            }
        });

        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(24);
        numberPicker.setWrapSelectorWheel(false);


    }

    public void startBAC() {
        Intent intent;
        intent = new Intent(this, BAC.class);
        startActivity(intent);
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
