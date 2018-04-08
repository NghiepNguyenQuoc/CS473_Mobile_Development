package com.example.vynguyen.lab4_prob1;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;

public class FoodActivity extends Activity {
    ListView lv;

    // Init data
    String[] valuesFood = new String[] {
            "Alcohol",
            "Breakfast",
            "Chocolate Cake",
            "Coffee",
            "Donut",
            "French Fries",
            "Hamburger",
            "Ice Cream",
            "Pasta"
    };
    int[] imageId = new int[] {
            R.drawable.alcohol,
            R.drawable.breakfast,
            R.drawable.chocolatecake,
            R.drawable.coffee,
            R.drawable.donut,
            R.drawable.frenchfries,
            R.drawable.hamburger,
            R.drawable.icecream,
            R.drawable.pasta,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        // Get view object
        lv = findViewById(R.id.foodList);
        lv.setAdapter(new FoodAdapter(this, valuesFood, imageId));



    }
}
