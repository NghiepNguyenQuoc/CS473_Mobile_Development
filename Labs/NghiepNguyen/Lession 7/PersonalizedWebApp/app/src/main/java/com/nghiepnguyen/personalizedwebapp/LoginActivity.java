package com.nghiepnguyen.personalizedwebapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText email_edittext, password_edittext;
    private Button signin_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_edittext = findViewById(R.id.email_edittext);
        password_edittext = findViewById(R.id.password_edittext);
        signin_button = findViewById(R.id.signin_button);

        final List<User> userList = new ArrayList<>();
        User user1 = new User("nghiepnguyen@gmail.com", "123456");
        User user2 = new User("nghiepmum@gmail.com", "456789");
        User user3 = new User("mumnghiep@gmail.com", "789456");
        User user4 = new User("nghiepvn@gmail.com", "147258");
        User user5 = new User("quocnguyen@gmail.com", "258369");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email_edittext.getText()) || TextUtils.isEmpty(password_edittext.getText()))
                    Toast.makeText(LoginActivity.this, "Email and password can not beblank!!!", Toast.LENGTH_LONG).show();
                else {
                    String userName = email_edittext.getText().toString();
                    String passWord = password_edittext.getText().toString();
                    User user = new User(userName, passWord);
                    if (userList.contains(user)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("account", email_edittext.getText().toString());
                        startActivity(intent);
                        finish();
                    } else
                        Toast.makeText(LoginActivity.this, "Your account is not exist!!!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
