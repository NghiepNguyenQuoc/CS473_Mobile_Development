package com.example.vynguyen.lab4_prob1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Received intent and data
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        // Set welcome string
        TextView tv = (TextView)findViewById(R.id.welcomUser);
        tv.setText("Welcome " + email);

    }

    public void onClick(View view) {
        String catName = "";
        switch (view.getId()) {
            case R.id.electronics:
                catName = "Electronics";
                break;
            case R.id.clothing:
                catName = "Clothing";
                break;
            case R.id.beauty:
                catName = "Beauty";
                break;
            case R.id.food:
                catName = "Food";
                break;
        }

        // Toast
        Context context = getApplicationContext();
        CharSequence text = "You have chosen the " + catName + " category of shopping";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
