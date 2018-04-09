package com.swa.todo.adapter;

import java.util.List;

import com.swa.todo.R;
import com.swa.todo.TagEnum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TagSpinnerAdapter extends ArrayAdapter<TagEnum> {

	private Context ctx;
	private List<TagEnum> tagList;
	
	public TagSpinnerAdapter(Context ctx, List<TagEnum> tagList) {
		super(ctx, R.layout.spinner_tag_layout);
		this.ctx = ctx;
		this.tagList = tagList;
	}

	@Override
	public int getCount() {
		return tagList.size();
	}

	@Override
	public TagEnum getItem(int position) {
		
		return tagList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return tagList.get(position).hashCode();
	}

	
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return _getView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return _getView(position, convertView, parent);
	}

	private View _getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			// Inflate spinner layout
			LayoutInflater inf = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inf.inflate(R.layout.spinner_tag_layout, parent, false);
		}
		
		// We should use ViewHolder pattern
		ImageView iv = (ImageView) v.findViewById(R.id.tagSpinnerImage);
		TextView tv = (TextView) v.findViewById(R.id.tagNameSpinner);
		
		TagEnum t = tagList.get(position);
		
		iv.setBackgroundResource(t.getTagColor());
		tv.setText(t.getName());
		
		return v;
	}
	
	
}
