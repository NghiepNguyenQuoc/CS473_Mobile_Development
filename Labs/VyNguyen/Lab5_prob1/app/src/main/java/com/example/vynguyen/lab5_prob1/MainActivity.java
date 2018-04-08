package com.example.vynguyen.lab5_prob1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get auto complete text view
        AutoCompleteTextView actv = (AutoCompleteTextView)findViewById(R.id.chooseMovie);

        // Get value from resourse
        String[] values = getResources().getStringArray(R.array.movies);

        // Pass values to array adapter
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        actv.setAdapter(ad);
        actv.setThreshold(1);

        // Get value is selected
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Item selected is: " +
                                parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
