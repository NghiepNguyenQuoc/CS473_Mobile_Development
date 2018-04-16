package com.example.vynguyen.lab7_prob1;

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

public class MainActivity extends AppCompatActivity {
    final static List<User> lstUsers = new ArrayList<>();
    public static void initUser() {
        lstUsers.add(new User("vynguyenlc", "123"));
        lstUsers.add(new User("lcvnguyen", "1234"));
        lstUsers.add(new User("lcvnguyenmum", "12345"));
        lstUsers.add(new User("abc@gmail", "123456"));
        lstUsers.add(new User("abcmum", "1234567"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init data
        initUser();

        // Action sign in
        final Button signIn = (Button)findViewById(R.id.signInButton);
        final EditText userName = (EditText)findViewById(R.id.userName);
        final EditText password = (EditText)findViewById(R.id.password);
        final TextView message = (TextView)findViewById(R.id.message);

        // Get share preference
        final SharedPreferences sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);

        // Check share preference
        String uName = sharedPreferences.getString("userName", "");
        String pass = sharedPreferences.getString("pass", "");
        if (!uName.isEmpty()) userName.setText(uName);
        if (!pass.isEmpty()) password.setText(pass);

        //
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check valid user email and password
                String user = userName.getText().toString();
                String pass = password.getText().toString();
                boolean isExist = false;
                for (User u : lstUsers) {
                    if (user.equals(u.getUserName()) && pass.equals(u.getPassword())) {
                        // Store share preference
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userName", user);
                        editor.putString("pass", pass);

                        isExist = true;
                        // Go to another activity
                        signIn(v);
                        break;
                    }
                }

                // Show message to user
                if (!isExist) {
                    message.setText("Your email is not exists or password is not corrected, please resign in");
                }
            }
        });
    }

    // Go to another activity
    public void signIn(View view){
        EditText userName = (EditText)findViewById(R.id.userName);
        Intent intent = new Intent(this, MainPageActivity.class);
        intent.putExtra("userName",userName.getText().toString());
        startActivity(intent);
    }
}
