package com.example.vynguyen.lab4_prob1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class BeautyActivity extends Activity {
    // Get view object
    Spinner lstBeauties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);

        //
        lstBeauties = (Spinner)findViewById(R.id.beautyList);
        lstBeauties.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String product = (String)parent.getItemAtPosition(position);
                String message = "You chose the " + product + " item.";
                // Toast message
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
