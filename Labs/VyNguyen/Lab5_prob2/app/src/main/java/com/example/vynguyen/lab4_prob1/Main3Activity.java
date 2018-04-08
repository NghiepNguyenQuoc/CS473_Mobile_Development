package com.example.vynguyen.lab4_prob1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Get value
        final EditText firstName = (EditText)findViewById(R.id.firstName);
        final EditText lastName = (EditText)findViewById(R.id.lastName);
        final EditText email = (EditText)findViewById(R.id.newEmail);
        final EditText password = (EditText)findViewById(R.id.newPassword);
        final TextView wMess = (TextView)findViewById(R.id.warning);

        // Create account click
        Button create = (Button)findViewById(R.id.createAccount);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check empty
                String warningMess = "Empty";
                if (firstName.getText().toString().isEmpty()) {
                    warningMess = warningMess + " First name ";
                }
                if (lastName.getText().toString().isEmpty()) {
                    warningMess = warningMess + " Last name ";
                }
                if (email.getText().toString().isEmpty()) {
                    warningMess = warningMess + " Email ";
                }
                if (password.getText().toString().isEmpty()) {
                    warningMess = warningMess + " Password ";
                }

                if (warningMess.length() > 5) {
                    wMess.setText(warningMess);
                } else {
                    // toast
                    Context context = getApplicationContext();
                    CharSequence text = "Account created successfully";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    // Pass intent data back
                    Intent data = new Intent();
                    String e = email.getText().toString();
                    String pass = password.getText().toString();
                    // fetch
                    data.putExtra("email", e);
                    data.putExtra("pass", pass);
                    setResult(RESULT_OK, data);
                    // close
                    finish();
                }
            }
        });
    }
}
