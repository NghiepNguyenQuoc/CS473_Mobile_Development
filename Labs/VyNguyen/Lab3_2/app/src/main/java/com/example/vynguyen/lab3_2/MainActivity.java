package com.example.vynguyen.lab3_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    final static ArrayList<String> foods = new ArrayList<String>();
    Integer random = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init array
        foods.add("Hamburger");
        foods.add("Pizza");
        foods.add("Mexican");
        foods.add("American");
        foods.add("Chinese");

        // action for button decide
        Button decide = findViewById(R.id.buttonDecide);
        final TextView foodName = findViewById(R.id.foodName);
        decide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random++;
                if (random >= foods.size()) {
                    random = 0;
                }

                foodName.setText(foods.get(random));
            }
        });

        // action for button add food
        Button addFood = findViewById(R.id.addFood);
        final EditText newFoodName = findViewById(R.id.newFoodName);
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!newFoodName.getText().toString().isEmpty()) {
                    // add to array
                    foods.add(newFoodName.getText().toString());
                }
            }
        });
    }
}
