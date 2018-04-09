package com.example.rmohanraj.myapplication;

import android.app.TaskStackBuilder;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void notify1(View v){
        // 1. Create an object for NotificationManager and get the instance
        NotificationManager nManager=
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    //    NotificationCompat.Builder builder=new
      //          NotificationCompat.Builder(this);
        // 2. Create an Builder object for the Notification to set the icon,title and other properties
        Notification.Builder builder = new Notification.Builder(this);

        builder.setSmallIcon(R.drawable.search_logo);
        builder.setTicker("Message to Show when Notification pops up");
        builder.setContentTitle("My Notification1");
        builder.setContentText("Sample Notification1 for Demo...");

             Bitmap bmp= BitmapFactory.decodeResource(getResources(),
                R.drawable.twitter_logo);
            builder.setLargeIcon(bmp);
        // 3. Create a Pending Intent for the Builder
        Intent i=new Intent();
        i.setComponent(new ComponentName(getApplicationContext(),
                MainActivity.class));

        PendingIntent pIntent=PendingIntent.getActivity(getApplicationContext(),
                0,i,0);
        builder.setContentIntent(pIntent);

        //4. Send the Notification to the Notification Manager  by calling notify() method
        nManager.notify(1,builder.getNotification());
    }

    public void notify2(View v){
        // 1. Create an object for NotificationManager and get the instance
        NotificationManager nManager=
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        // 2. Create an Builder object for the Notification to set the icon,title and other properties
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.twitter_logo);
        builder.setTicker("Message to Show when Notification pops up");
        builder.setContentTitle("My Notification2");
        builder.setContentText("Sample Notification2 for Demo...");

        Bitmap bmp= BitmapFactory.decodeResource(getResources(),
                R.drawable.search_logo);
        builder.setLargeIcon(bmp);

        // 3. Create a new Intent for the Notification
        Intent i=new Intent(getApplicationContext(),NotifyActivity.class);
        // 4. Create TaskStackBuilder Object
        TaskStackBuilder tsb = TaskStackBuilder.create(this);
       // Add the activity parent chain as specified by manifest elements to the task stack builder.
        tsb.addParentStack(NotifyActivity.class);
        // add the intent to the top of stack
        tsb.addNextIntent(i);
        // Create a Pending Intent for the builder
        PendingIntent pIntent=tsb.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pIntent);

        //4. Send the Notification to the Notification Manager  by calling notify() method
        nManager.notify(0,builder.getNotification());
    }
}
