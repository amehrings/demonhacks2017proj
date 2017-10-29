package com.example.sam.safedrinkingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Sam on 10/28/2017.
 */

public class DrinksFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.content_bac,container,false);
        return v;
    }
    public void onStart() {
        super.onStart();
        TextView textView6 = getView().findViewById(R.id.textView6);
        textView6.setText(String.format("%.2f", MainActivity.drunkCalc));

        TextView textView7 = getView().findViewById(R.id.textView7);
        textView7.setText("Hard Liquor (Shot): " + String.format("%.2f", MainActivity.hardLiquorShot));

        TextView textView8 = getView().findViewById(R.id.textView8);
        textView8.setText("Beer (12oz. @ 5%): " + String.format("%.2f", MainActivity.beer));

        TextView textView9 = getView().findViewById(R.id.textView9);
        textView9.setText("Wine Bottle (25 oz. @ 12%): " + String.format("%.2f", MainActivity.wine));
    }
}
