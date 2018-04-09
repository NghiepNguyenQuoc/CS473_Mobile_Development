package com.example.rmohanraj.actvdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView actv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actv = (AutoCompleteTextView) findViewById(R.id.actv);
        // Get the XML configured vales into the Activity and stored into an String Array
      //  String[] values = getResources().getStringArray(R.array.countries);
        String[] values = new String[]{"Asia","Australia","America","Belgium","Brazil","Canada","California","Dubai","France","Paris" };
        /* Pass three parameters to the ArrayAdapter
         1. The current context,
         2. The resource ID for a built-in layout file containing a TextView to use when instantiating views,
            which are available in android.R.layout
         3. The objects to represent in the values
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,values);
        actv.setAdapter(adapter);
        actv.setThreshold(1);

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  Toast.makeText(getApplicationContext(),"Item selected is : "+parent.getItemIdAtPosition(position),Toast.LENGTH_LONG).show();
                String val = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item selected is : "+val,Toast.LENGTH_LONG).show();
            }
        });
    }
}
