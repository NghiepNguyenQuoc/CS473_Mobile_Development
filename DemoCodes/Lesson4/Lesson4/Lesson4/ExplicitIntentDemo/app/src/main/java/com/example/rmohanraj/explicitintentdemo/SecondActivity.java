package com.example.rmohanraj.explicitintentdemo;

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
        int x = intent.getIntExtra("message",34);
        TextView tv = (TextView) findViewById(R.id.rmsg);
        tv.setText(output+x);
    }
}
