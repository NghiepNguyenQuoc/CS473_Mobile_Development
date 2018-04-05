package com.nghiepnguyen.lession4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingCategory extends AppCompatActivity {

    private View beautyView, electronicView, clothingView, foodView;
    private TextView account_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_activity);
        electronicView = findViewById(R.id.electronic_view);
        clothingView = findViewById(R.id.clothing_view);
        beautyView = findViewById(R.id.beauty_view);
        foodView = findViewById(R.id.food_view);
        account_textview = findViewById(R.id.account_textview);
        account_textview.setText("Welcome " + getIntent().getExtras().getString("account"));

        electronicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShoppingCategory.this, "You have chosen the Electronic category of shopping", Toast.LENGTH_LONG).show();

            }
        });

        clothingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShoppingCategory.this, "You have chosen the Clothing category of shopping", Toast.LENGTH_LONG).show();

            }
        });

        beautyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShoppingCategory.this, "You have chosen the Beauty category of shopping", Toast.LENGTH_LONG).show();

            }
        });

        foodView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShoppingCategory.this, "You have chosen the Food category of shopping", Toast.LENGTH_LONG).show();
            }
        });
    }
}
