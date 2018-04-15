package com.nghiepnguyen.lession5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class ElectronicDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_detail);

        Intent intent = getIntent();

        Product product = intent.getExtras().getParcelable("Product");
        ImageView imageView = findViewById(R.id.product_imageview);
        TextView textView = findViewById(R.id.info_textview);

        imageView.setImageResource(product.getImageResource());
        textView.setText(product.getTitle() + "\n\nColor: " + product.getColor() + "\n\nWalmart #: " + product.getItemid() + "\n\nItem Description: \n" + product.getDesc());
    }
}
