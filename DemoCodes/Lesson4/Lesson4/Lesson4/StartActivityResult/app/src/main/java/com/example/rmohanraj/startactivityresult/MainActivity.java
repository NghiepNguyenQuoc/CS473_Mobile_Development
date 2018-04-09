package com.example.rmohanraj.startactivityresult;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout l = new LinearLayout(this);
        Button b = new Button(this);
        b.setText("Please Press");
        b.setOnClickListener(bListener);
        t = new TextView(this);
        t.setText("Result will go here");
        t.setTextColor(Color.GRAY);
        l.addView(b);
        l.addView(t);

        setContentView(l);
    }

    View.OnClickListener bListener = new View.OnClickListener(){
        @Override
        public void onClick(View v)
        {
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            startActivityForResult(intent, 1);
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                t.setTextColor(Color.RED);
                String returnedResult = data.getData().toString();
                t.setText(returnedResult);
            }
        }
    }
}
