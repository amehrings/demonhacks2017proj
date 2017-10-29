package com.example.sam.safedrinkingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Sam on 10/28/2017.
 */

public class BACFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_main2_2,container,false);
        return v;
    }
    public void onStart() {
        super.onStart();
        final GlobalVars gv = (GlobalVars) getActivity().getApplicationContext();
        final NumberPicker numberPicker = getView().findViewById(R.id.numberPicker2);
        final double[] bac = new double[1];
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(24);
        numberPicker.setWrapSelectorWheel(false);
        final Spinner spinner = getView().findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.alcohol, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Button submit = getView().findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gv.getSex().equals("Male")) {
                    if (spinner.getSelectedItem().toString() == "Beer(s)") {
                        bac[0] = (((.05 * 12) * 5.14) / (gv.getWeight() * gv.getMaleConst())) - (.015 * numberPicker.getValue());
                    } else if (spinner.getSelectedItem().toString() == "Spirit(s)") {
                        bac[0] = (((.40*1.25) * 5.14) / (gv.getWeight() * gv.getMaleConst())) - (.015 * numberPicker.getValue());
                    } else {
                        bac[0] = (((.12*25.4) * 5.14) / (gv.getWeight() * gv.getMaleConst())) - (.015 * numberPicker.getValue());
                    }
                } else {
                    if (spinner.getSelectedItem().toString() == "Beer(s)") {
                        bac[0] = (((.05 * 12) * 5.14) / (gv.getWeight() * gv.getFemaleConst())) - (.015 * numberPicker.getValue());
                    } else if (spinner.getSelectedItem().toString() == "Spirit(s)") {
                        bac[0] = (((.40*1.25) * 5.14) / (gv.getWeight() * gv.getFemaleConst())) - (.015 * numberPicker.getValue());
                    } else {
                        bac[0] = (((.12*25.4) * 5.14) / (gv.getWeight() * gv.getFemaleConst())) - (.015 * numberPicker.getValue());
                    }
                }
                TextView baclevel = getView().findViewById(R.id.textView12);
                baclevel.setText(String.valueOf(bac[0]));
            }
        });

    }
}
