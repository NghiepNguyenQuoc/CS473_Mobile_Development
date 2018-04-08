package com.example.vynguyen.lab4_prob1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodAdapter extends BaseAdapter {
    private Activity foodActivity;
    private String[] foodList;
    private int[] foodIdImage;
    private static LayoutInflater layoutInflater = null;

    public FoodAdapter(Activity foodActivity, String[] foodList, int[] foodIdImage){
        this.foodActivity = foodActivity;
        this.foodList = foodList;
        this.foodIdImage = foodIdImage;
        //layoutInflater = LayoutInflater.from(FoodActivity.context);
        layoutInflater = this.foodActivity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return foodList.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.activity_food_view_in_list, null, true);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageViewFood);
        final TextView textView = (TextView)view.findViewById(R.id.textViewFood);

        imageView.setImageResource(foodIdImage[position]);
        textView.setText(foodList[position]);

        //View rowView = layoutInflater.inflate(R.layout.activity_food_view_in_list, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(foodActivity, "You chose the " + textView.getText().toString() + " item.", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
