package com.example.rmohanraj.vibgyorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FindColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_color);
    }
    public void onClickFindColor(View view){
        TextView colors = (TextView) findViewById(R.id.purpose);
        // Get a reference to the Spinner
        Spinner color = (Spinner) findViewById(R.id.color);
        // Get the selected item in the spinner
        String colorname = String.valueOf(color.getSelectedItem());
        // Display the selected item
      switch(colorname){
          case  "Violet" :colors.setText("Highest element of spirituality");
                 break;
          case  "Indigo" :colors.setText("spiritual attainment, psychic abilities, self awareness and enhancement of Intuition");
              break;


      }

    }
}
