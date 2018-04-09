package com.example.rmohanraj.listviewcustomdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    // Access without object reference
     static MainActivity mainActivity;
    ListView lview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        lview=(ListView)findViewById(R.id.lview1);
        lview.setAdapter(new MyAdapter()); // Pass the reference of own Adapter object
    }
    // To refresh the ListView once elements are removed by clicking the Del button
    public  void refresh(){
        lview.setAdapter(new MyAdapter()); // View get refreshed without the removed element
    }
}
