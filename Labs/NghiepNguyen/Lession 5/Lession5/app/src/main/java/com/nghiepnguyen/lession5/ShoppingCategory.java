package com.nghiepnguyen.lession5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingCategory extends AppCompatActivity implements View.OnClickListener {

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

        ActionBar aBar = getSupportActionBar();
        aBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        final ActionBar.TabListener listener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                if (tab.getText().equals("Electronic"))
                    onClickAction(R.id.electronic_view);
                else if (tab.getText().equals("Clothing"))
                    onClickAction(R.id.clothing_view);
                else if (tab.getText().equals("Beauty"))
                    onClickAction(R.id.beauty_view);
                else if (tab.getText().equals("Food"))
                    onClickAction(R.id.food_view);
                else if (tab.getText().equals("Logout")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("");
                    builder.setMessage("Do you want to Exit");
                    AlertDialog.OnClickListener clickListener = new AlertDialog.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (i == DialogInterface.BUTTON_NEGATIVE) {
                                dialogInterface.dismiss();
                            } else if (i == DialogInterface.BUTTON_POSITIVE) {
                                dialogInterface.dismiss();
                                finish();
                            }
                        }
                    };
                    builder.setPositiveButton("Yes", clickListener);
                    builder.setPositiveButton("No", clickListener);
                    builder.show();
                }
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };

        aBar.addTab(aBar.newTab().setText("Electronic").setTabListener(listener));
        aBar.addTab(aBar.newTab().setText("Clothing").setTabListener(listener));
        aBar.addTab(aBar.newTab().setText("Beauty").setTabListener(listener));
        aBar.addTab(aBar.newTab().setText("Food").setTabListener(listener));
        aBar.addTab(aBar.newTab().setText("Logout").setTabListener(listener));

        /*set on click action*/
        electronicView.setOnClickListener(this);
        clothingView.setOnClickListener(this);
        beautyView.setOnClickListener(this);
        foodView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onClickAction(view.getId());
    }

    private void onClickAction(int id) {
        Intent intent;
        switch (id) {
            case R.id.electronic_view:
                Toast.makeText(ShoppingCategory.this, "You have chosen the Electronic category of shopping", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(), ElectronicActivity.class);
                startActivity(intent);
                break;
            case R.id.beauty_view:
                Toast.makeText(ShoppingCategory.this, "You have chosen the Beauty category of shopping", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(), BeautyActivity.class);
                startActivity(intent);
                break;
            case R.id.clothing_view:
                Toast.makeText(ShoppingCategory.this, "You have chosen the Clothing category of shopping", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(), ClothingActivity.class);
                startActivity(intent);
                break;
            case R.id.food_view:
                Toast.makeText(ShoppingCategory.this, "You have chosen the Food category of shopping", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(), FoodActivity.class);
                startActivity(intent);
                break;
        }
    }
}
