package com.example.vynguyen.lab4_prob1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ElectronicItemDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_item_detail);

        // Receive data
        Product product = (Product)getIntent().getSerializableExtra("product");

        // Get object view
        ImageView imageView = (ImageView)findViewById(R.id.imageElectronicDetail);
        final TextView name = (TextView)findViewById(R.id.title);
        TextView idNumber = (TextView)findViewById(R.id.idNumber);
        TextView color = (TextView)findViewById(R.id.color);
        TextView description = (TextView)findViewById(R.id.description);

        // Set value
        imageView.setImageResource(product.getImage());
        name.setText(product.getTitle());
        idNumber.setText("ID: " + product.getItemID());
        color.setText("Color: " + product.getColor());
        description.setText("    " + product.getDescription());
    }
}
