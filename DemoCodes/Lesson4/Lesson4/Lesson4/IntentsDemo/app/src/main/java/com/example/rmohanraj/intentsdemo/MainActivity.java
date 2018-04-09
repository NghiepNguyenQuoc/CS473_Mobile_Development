package com.example.rmohanraj.intentsdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onSendMessageExplicit(View view){
        et1 = (EditText) findViewById(R.id.smsg);
        String input = et1.getText().toString();
    //    Intent intent = new Intent(this,SecondActivity.class);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this,SecondActivity.class));
        intent.putExtra("message",input);
        startActivity(intent);
    }

    public void onSendMessageImplicit(View view){
        et1 = (EditText) findViewById(R.id.smsg);
        String input = et1.getText().toString();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,input);
        startActivity(intent);
    }
    public void dial(View view){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_DIAL);
        EditText et2 = (EditText) findViewById(R.id.tel);
        i.setData(Uri.parse("tel:"+et2.getText().toString()));
        startActivity(i);
    }
    public void whatsapp(View view){
        Intent i = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
        startActivity(i);
    }
}
