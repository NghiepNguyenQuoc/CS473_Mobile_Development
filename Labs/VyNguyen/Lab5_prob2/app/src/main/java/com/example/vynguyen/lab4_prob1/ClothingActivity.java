package com.example.vynguyen.lab4_prob1;

import android.app.Activity;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ClothingActivity extends Activity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);

        // Build array list values
        String[] values = new String[]{
                "Jacket",
                "T-shirt",
                "Skirt",
                "Legging",
                "Sweater",
                "Dress",
                "Jean",
        };

        // Get object view
        lv = (ListView)findViewById(R.id.lstClothing);

        // Define adapter and add value
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView)view.findViewById(android.R.id.text1);

                tv.setTextColor(Color.WHITE);

                return view;
            }
        };

        lv.setAdapter(adapter);

        // onclick
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get item selected
                String item = (String)lv.getItemAtPosition(position);
                String message = "You chose the " + item + " item.";

                // Toast
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
