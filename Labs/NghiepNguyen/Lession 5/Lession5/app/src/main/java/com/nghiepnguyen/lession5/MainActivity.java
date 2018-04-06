package com.nghiepnguyen.lession5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final int REQUEST_CODE_CREATE_ACCOUNT = 1001;
    private EditText email_edittext, password_edittext;
    private TextView forgot_password_textview, create_new_account_textview;
    private Button signin_button;

    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email_edittext = findViewById(R.id.email_edittext);
        password_edittext = findViewById(R.id.password_edittext);
        forgot_password_textview = findViewById(R.id.forgot_password_textview);
        create_new_account_textview = findViewById(R.id.create_new_account_textview);
        signin_button = findViewById(R.id.signin_button);

        signin_button.setOnClickListener(this);
        create_new_account_textview.setOnClickListener(this);
        forgot_password_textview.setOnClickListener(this);


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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signin_button:
                if (TextUtils.isEmpty(email_edittext.getText()) || TextUtils.isEmpty(password_edittext.getText()))
                    Toast.makeText(MainActivity.this, "Email and password can not beblank!!!", Toast.LENGTH_LONG).show();
                else {
                    User user = new User(email_edittext.getText().toString(), password_edittext.getText().toString());
                    if (userList.contains(user)) {
                        Intent intent = new Intent(MainActivity.this, ShoppingCategory.class);
                        intent.putExtra("account", email_edittext.getText().toString());
                        startActivity(intent);
                        finish();
                    } else
                        Toast.makeText(MainActivity.this, "Your account is not exist!!!", Toast.LENGTH_LONG).show();

                }
                break;
            case R.id.create_new_account_textview:
                Intent createIntent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivityForResult(createIntent, REQUEST_CODE_CREATE_ACCOUNT);
                break;
            case R.id.forgot_password_textview:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Here is your temp password: 123456");
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CREATE_ACCOUNT && resultCode == RESULT_OK) {
            String email = data.getStringExtra("email");
            String password = data.getStringExtra("password");
            userList.add(new User(email, password));
            email_edittext.setText(email);
            password_edittext.setText(password);
        }
    }
}
