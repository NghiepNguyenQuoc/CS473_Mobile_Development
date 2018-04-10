package com.example.vynguyen.lab4_prob1;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.drm.DrmStore;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Received intent and data
        Intent intent = getIntent();
        final Activity context = this;
        String email = intent.getStringExtra("email");

        // Set welcome string
        TextView tv = (TextView)findViewById(R.id.welcomUser);
        tv.setText("Welcome " + email);

        // Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener listener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                String tabName = tab.getText().toString();

                Intent intent;
                switch (tabName) {
                    case "Electronics":
                        intent = new Intent(context, ElectronicViewActivity.class);
                        startActivity(intent);
                        break;
                    case "Clothing":
                        intent = new Intent(context, ClothingActivity.class);
                        startActivity(intent);
                        break;
                    case "Beauty":
                        intent = new Intent(context, BeautyActivity.class);
                        startActivity(intent);
                        break;
                    case "Food":
                        intent = new Intent(context, FoodActivity.class);
                        startActivity(intent);
                        break;
                    case "Logout":
                        exitClick();
                }
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };
        actionBar.addTab(actionBar.newTab().setText("Electronics").setTabListener(listener), false);
        actionBar.addTab(actionBar.newTab().setText("Clothing").setTabListener(listener), false);
        actionBar.addTab(actionBar.newTab().setText("Beauty").setTabListener(listener), false);
        actionBar.addTab(actionBar.newTab().setText("Food").setTabListener(listener), false);
        actionBar.addTab(actionBar.newTab().setText("Logout").setTabListener(listener),false);

    }

    public void onClick(View view) {
        String catName = "";
        switch (view.getId()) {
            case R.id.electronics:
            //case R.id.electricToolBar:
                catName = "Electronics";
                break;
            case R.id.clothing:
            //case R.id.clothingToolBar:
                catName = "Clothing";
                break;
            case R.id.beauty:
            //case R.id.beautyToolBar:
                catName = "Beauty";
                break;
            case R.id.food:
            //case R.id.foodToolBar:
                catName = "Food";
                break;
        }

        // Toast
        Context context = getApplicationContext();
        CharSequence text = "You have chosen the " + catName + " category of shopping";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent;
        // Go to product activity
        switch (view.getId()) {
            case R.id.electronics:
            //case R.id.electricToolBar:
                // Go to electronic activity
                intent = new Intent(this, ElectronicViewActivity.class);
                startActivity(intent);
                break;
            case R.id.clothing:
            //case R.id.clothingToolBar:
                // Go to clothing activity
                intent = new Intent(this, ClothingActivity.class);
                startActivity(intent);
                break;
            case R.id.beauty:
            //case R.id.beautyToolBar:
                // Go to beauty activity
                intent = new Intent(this, BeautyActivity.class);
                startActivity(intent);
                break;
            case R.id.food:
            //case R.id.foodToolBar:
                // Go to food activity
                intent = new Intent(this, FoodActivity.class);
                startActivity(intent);
                break;
        }
    }

    public  void  exitClick() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Exit Dialog");
        alert.setMessage("Do you want to exit?");
        //alert.setIcon(R.drawable.exit);

        AlertDialog.OnClickListener listener = new AlertDialog.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == dialog.BUTTON_POSITIVE){
                    dialog.dismiss();
                    finish();
                } else if (which == dialog.BUTTON_NEGATIVE){
                    dialog.dismiss();
                }
            }
        };

        alert.setPositiveButton("Yes", listener);
        alert.setNegativeButton("No", listener);

        alert.show();
    }
}
