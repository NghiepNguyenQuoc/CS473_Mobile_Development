package com.example.vynguyen.lab4_prob1;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ElectronicAdapter extends BaseAdapter {
    private Activity electronicActivity;
    private List<Product> lstProduct;
    private LayoutInflater inflater;

    public ElectronicAdapter(Activity electronicActivity, List<Product> lstProduct){
        this.electronicActivity = electronicActivity;
        this.lstProduct = lstProduct;
        this.inflater = this.electronicActivity.getLayoutInflater();
    }

    @Override
    public int getCount() {
            return lstProduct.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view = inflater.inflate(R.layout.activity_electronic_detail, null, true);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageElectronic);
        final TextView name = (TextView)view.findViewById(R.id.electronicName);
        TextView price = (TextView)view.findViewById(R.id.electronicPrice);
        TextView color = (TextView)view.findViewById(R.id.electronicColor);

        imageView.setImageResource(lstProduct.get(position).getImage());
        name.setText(lstProduct.get(position).getTitle());
        price.setText("Price: " + Double.toString(lstProduct.get(position).getPrice()));
        color.setText("Color: " + lstProduct.get(position).getColor());

        // Toast message
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(electronicActivity, "You chose the " + name.getText().toString() + " item.", Toast.LENGTH_LONG).show();

                // Create intent and go to detail activity
                Intent intent = new Intent(electronicActivity, ElectronicItemDetailActivity.class);
                intent.putExtra("product", lstProduct.get(position));
                electronicActivity.startActivity(intent);
            }
        });

        return view;
    }
}
