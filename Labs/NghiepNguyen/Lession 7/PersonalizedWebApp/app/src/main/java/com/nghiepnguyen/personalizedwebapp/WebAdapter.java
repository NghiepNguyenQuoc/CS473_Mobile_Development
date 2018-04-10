package com.nghiepnguyen.personalizedwebapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 986375 on 4/10/2018.
 */

public class WebAdapter extends BaseAdapter {
    private Context mContext;
    private List<WebModel> webModels;

    public WebAdapter(Context context, List<WebModel> webModels) {
        this.mContext = context;
        this.webModels = webModels;
    }

    @Override
    public int getCount() {
        return webModels.size();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.web_item, null, true);
            viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.website_logo_imageview);
            viewHolder.textView = view.findViewById(R.id.website_name_textview);
            viewHolder.view = view.findViewById(R.id.go_button);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final WebModel webModel = webModels.get(i);
        viewHolder.imageView.setImageResource(webModel.getImageResource());
        viewHolder.textView.setText(webModel.getName());
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("URL", webModel.getUrl());
                mContext.startActivity(intent);
            }
        });
        return null;
    }

    class ViewHolder {
        TextView textView;
        ImageView imageView;
        View view;
    }
}
