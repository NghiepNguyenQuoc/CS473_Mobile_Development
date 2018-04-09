package com.example.rmohanraj.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {
       @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
    public void alert(View view) {
        // 1. Create an object for AlertDialog by passing the current context object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 2. Set the basic information for the builder object
        builder.setTitle("Alert Message");
        builder.setMessage("Do you want to Exit");
        builder.setIcon(R.drawable.alerticon);
        // 3. Implement the listener for the buttons you specified on Alert dialog
        AlertDialog.OnClickListener listener = new AlertDialog.OnClickListener(){
            // Write you logic for button click actions based on which buttin is clicked from the alert dialog
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==dialog.BUTTON_POSITIVE){
                    dialog.dismiss(); // dismiss the dialog
                    finish(); // to destroy the activity
                }
                else if(which==dialog.BUTTON_NEGATIVE){
                    dialog.dismiss(); // dismiss the dialog, but activity is still alive
                }
            }
        };
        // 4. Here we are going to set two buttons on the Alert dialog with listener for click event
        builder.setPositiveButton("Yes",listener);
        builder.setNegativeButton("No",listener);
        // 5. need to show the dialog
        builder.show();
    }
}
