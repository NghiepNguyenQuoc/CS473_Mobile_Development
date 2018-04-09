package com.example.rmohanraj.startactivityresult;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class SecondActivity extends AppCompatActivity {
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout l = new LinearLayout(this);
        input = new EditText(this);
        Button b = new Button(this);
        b.setText("Please Press after Entering Text");
        b.setOnClickListener(bListener);

        l.addView(input);
        l.addView(b);
        setContentView(l);
    }

    View.OnClickListener bListener = new View.OnClickListener(){
        @Override
        public void onClick(View v)
        {
            Intent data = new Intent();
            String text = input.getText().toString();
            //---set the data to pass back---
            data.setData(Uri.parse(text));
            setResult(RESULT_OK, data);
            //---close the activity---
            finish();
        }
    };

}
