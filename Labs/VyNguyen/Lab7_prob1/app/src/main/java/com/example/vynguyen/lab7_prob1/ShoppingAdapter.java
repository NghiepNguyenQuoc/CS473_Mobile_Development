package com.example.vynguyen.lab7_prob1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShoppingAdapter extends BaseAdapter {
    private Context shoppingFragment;
    private String[] shoppingList;
    private Integer[] shoppingImage;
    private String[] shoppingUrl;
    //private LayoutInflater layoutInflater;

    public ShoppingAdapter(Context shoppingFragment, String[] shoppingList, Integer[] shoppingImage,
                       String[] shoppingUrl){
        this.shoppingFragment = shoppingFragment;
        this.shoppingList = shoppingList;
        this.shoppingImage = shoppingImage;
        this.shoppingUrl = shoppingUrl;
    }

    @Override
    public int getCount() {
        return shoppingList.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(shoppingFragment).inflate(R.layout.activity_shopping_detail,
                                                                    null, true);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageShoppingDetail);
        TextView textView = (TextView) view.findViewById(R.id.textShoppingDetail);
        final ImageView button = (ImageView)view.findViewById(R.id.imageGoShoppingButton);


        imageView.setImageResource(shoppingImage[position]);
        textView.setText(shoppingList[position]);
        button.setTextDirection(position);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to web view activity
                Intent intent = new Intent(shoppingFragment,WebViewActivity.class);
                intent.putExtra("webUrl", shoppingUrl[button.getTextDirection()]);
                shoppingFragment.startActivity(intent);
            }
        });

        return view;
    }
}
