package com.example.rmohanraj.listviewsimpledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lview=(ListView)findViewById(R.id.lview1);
                // Retrieve the file from the Phone Internal Memeory
              //  String path = "/storage/sdcard0/";
                String path =  "/storage/emulated/0/";
        File f  = new File(path);
        String[] files = f.list();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,files);
        lview.setAdapter(adapter);
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), item,Toast.LENGTH_LONG).show();
            }
        });
    }
}
