package com.example.rmohanraj.intentsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String output = intent.getStringExtra("message");
        TextView tv = (TextView) findViewById(R.id.rmsg);
        tv.setText(output);
    }
}
