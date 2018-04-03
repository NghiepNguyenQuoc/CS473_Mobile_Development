package com.example.vynguyen.lab3_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // button convert
        Button bt = findViewById(R.id.convert);
        final EditText input = findViewById(R.id.input);
        final EditText output = findViewById(R.id.output);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer tInput = Integer.valueOf(input.getText().toString());
                // converting
                Double lbs = tInput * 0.45;
                output.setText(lbs.toString());
            }
        });
    }
}
