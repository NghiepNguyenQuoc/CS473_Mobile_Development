package com.example.vynguyen.lab7_prob1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodAdapter extends BaseAdapter {
    private Context foodFragment;
    private String[] foodList;
    private Integer[] foodImage;
    private String[] foodUrl;
    //private LayoutInflater layoutInflater;

    public FoodAdapter(Context foodFragment, String[] foodList, Integer[] foodImage,
                        String[] foodUrl){
        this.foodFragment = foodFragment;
        this.foodList = foodList;
        this.foodImage = foodImage;
        this.foodUrl = foodUrl;
        //layoutInflater = this.foodFragment.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return foodList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //View view = layoutInflater.inflate(R.layout.activity_food_detail, null, true);
        View view = LayoutInflater.from(foodFragment).inflate(R.layout.activity_food_detail, null, true);


        ImageView imageView = (ImageView)view.findViewById(R.id.imageFoodDetail);
        TextView textView = (TextView) view.findViewById(R.id.textFoodDetail);
        final ImageView button = (ImageView)view.findViewById(R.id.imageGoButton);


        imageView.setImageResource(foodImage[position]);
        textView.setText(foodList[position]);
        button.setTextDirection(position);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to web view activity
                Intent intent = new Intent(foodFragment,WebViewActivity.class);
                intent.putExtra("webUrl", foodUrl[button.getTextDirection()]);
                foodFragment.startActivity(intent);
            }
        });

        return view;
    }
}
