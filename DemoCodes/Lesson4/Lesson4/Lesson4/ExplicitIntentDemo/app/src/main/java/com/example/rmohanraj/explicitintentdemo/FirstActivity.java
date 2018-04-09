package com.example.rmohanraj.explicitintentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }
    public void onSendMessage(View view){
        et1 = (EditText) findViewById(R.id.smsg);
        String input = et1.getText().toString();
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("message",input); // Here message is a key to retrieve the input text in the second activity
        startActivity(intent);
    }
}
