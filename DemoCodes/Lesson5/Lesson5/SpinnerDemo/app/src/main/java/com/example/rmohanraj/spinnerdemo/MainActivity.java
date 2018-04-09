package com.example.rmohanraj.spinnerdemo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public  EditText wt;
    public double weight=0.0;
    public int value=0;
    String x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] values = {"Mercury","Venus","Earth","Mars","Jupiter","Saturn","Uranus","Neptune" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,values);
       final Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
        spinner.setAdapter(adapter);
             wt = (EditText) findViewById(R.id.mass);
        // Called when the focus state of a view has changed.
       wt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String item = parent.getItemAtPosition(position).toString();
                             x = wt.getText().toString();
                            value = Integer.parseInt(x);
                            int mass =value;
                            switch (item){
                                case "Mercury": weight = mass * 0.38; break;
                                case "Venus": weight = mass * 0.91; break;
                                case "Earth": weight = mass * 1; break;
                                case "Mars": weight = mass * 0.38; break;
                                case "Jupiter": weight = mass * 2.34; break;
                                case "Saturn": weight = mass * 0.93; break;
                                case "Uranus": weight = mass * 0.92; break;
                                case "Neptune": weight = mass * 0.91; break;
                                default : weight = 0.0; break;
                            }
                            Toast.makeText(MainActivity.this, weight + " LBs", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }
        });
       }
}
