package com.example.vynguyen.lab6_prob2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    // Create new user array
    final static List<User> lstUsers = new ArrayList<>();
    public static void initUser() {
        lstUsers.add(new User("vynguyenlc@gmail.com", "123"));
        lstUsers.add(new User("lcvnguyen@gmail.com", "1234"));
        lstUsers.add(new User("lcvnguyen@mum.edu", "12345"));
        lstUsers.add(new User("abc@gmail.com", "123456"));
        lstUsers.add(new User("abc@mum.edu", "1234567"));
    }

    // Add user
    public static void addUser(String email, String pass) {
        lstUsers.add(new User(email, pass));
    }

    // Share reference object
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init data
        initUser();

        // Action sign in
        final Button signIn = (Button)findViewById(R.id.signIn);
        final EditText userEmail = (EditText)findViewById(R.id.emailAddress);
        final EditText password = (EditText)findViewById(R.id.password);
        final TextView message = (TextView)findViewById(R.id.message);

        // Get share preference
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Check share preference data
        String preUserName = sharedPreferences.getString("userEmail", "");
        String prePassword = sharedPreferences.getString("userPassword", "");

        // Verify
        if(!preUserName.isEmpty()) userEmail.setText(preUserName);
        if(!prePassword.isEmpty()) password.setText(prePassword);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check valid user email and password
                String email = userEmail.getText().toString();
                String pass = password.getText().toString();
                boolean isExist = false;
                for (User u : lstUsers) {
                    if (email.equals(u.getUserName()) && pass.equals(u.getPassword())) {
                        // Add value to share preference
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userEmail", email);
                        editor.putString("userPassword", pass);
                        editor.commit();

                        isExist = true;
                        // Go to another activity
                        signIn(v);
                        break;
                    }
                }

                // Show message to user
                if (!isExist) {
                    message.setText("Your email is not exists or password is not corrected, please sign up or resign in");
                }
            }
        });

        // Action sign up
        final TextView signUp = (TextView)findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(v);
            }
        });

        // Action forgot password
        TextView forgotPass = (TextView)findViewById(R.id.forgotPassword);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Please help to reset my password");
                startActivity(intent);
            }
        });
    }

    // Go to another activity
    public void signIn(View view){
        EditText userEmail = (EditText)findViewById(R.id.emailAddress);
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("email",userEmail.getText().toString());
        startActivity(intent);
    }

    // Go to another activity
    public void signUp(View view){
        //Intent intent = new Intent(this, Main3Activity.class);
        //startActivityForResult(intent, 1);
        //startActivity(intent);
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // get result
                String e = data.getStringExtra("email");
                String pass = data.getStringExtra("pass");
                addUser(e, pass);
            }
        }
    }*/
}
