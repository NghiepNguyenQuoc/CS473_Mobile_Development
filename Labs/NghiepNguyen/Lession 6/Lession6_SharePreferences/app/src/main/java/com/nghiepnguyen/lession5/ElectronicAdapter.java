package com.nghiepnguyen.lession5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ElectronicAdapter extends BaseAdapter {
    private Context context;
    private List<Product> productList;

    public ElectronicAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_electronic, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.product_imageview);
            viewHolder.textView = view.findViewById(R.id.short_information_textview);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final Product product = productList.get(i);
        viewHolder.imageView.setImageResource(product.getImageResource());
        viewHolder.textView.setText(product.getTitle() + "\n\nPrice: $" + product.getPrice() + "\n\nColor: " + product.getColor());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ElectronicDetailActivity.class);
                intent.putExtra("Product", product);
                context.startActivity(intent);
            }
        });
        return view;
    }

    class ViewHolder {
        private ImageView imageView;
        private TextView textView;
    }
}
