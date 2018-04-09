package com.swa.todo;

import java.text.SimpleDateFormat;
import java.util.List;

import com.swa.todo.model.Item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ToDoItemAdapter extends ArrayAdapter<Item> {

	private Context ctx;
	private List<Item> itemList;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	@Override
	public Item getItem(int position) {
		if (itemList != null)
		   return itemList.get(position);
		
		return null;
	}

	@Override
	public long getItemId(int position) {
		if (itemList != null)
		  return itemList.get(position).hashCode();
		
		return 0;
	}

	

	@Override
	public int getCount() {
		if (itemList != null)
			return itemList.size();
		
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		ItemHolder h = null;
		
		if (v == null) {
			// Inflate row layout
			LayoutInflater inf = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inf.inflate(R.layout.item_layout, parent, false);
			
			// Look for Views in the layout
			ImageView iv = (ImageView) v.findViewById(R.id.tagView);
			TextView nameTv = (TextView) v.findViewById(R.id.nameView);
			TextView descrView = (TextView) v.findViewById(R.id.descrView);
			TextView dateView = (TextView) v.findViewById(R.id.dateView);
			
			h = new ItemHolder();
			h.tagView = iv;
			h.nameView = nameTv;
			h.descrView = descrView;
			h.dateView = dateView;
			
			v.setTag(h);
		}
		else 
		  h = (ItemHolder) v.getTag();
		
		h.nameView.setText(itemList.get(position).getName());		
		h.descrView.setText(itemList.get(position).getDescr());
		h.tagView.setBackgroundResource(itemList.get(position).getTag().getTagColor());
		h.dateView.setText(sdf.format(itemList.get(position).getDate()));
		
		return v;
	}

	public ToDoItemAdapter(Context context, List<Item> itemList) {
		super(context, R.layout.item_layout);
		this.ctx = context;
		this.itemList = itemList;
	}
	
	
	// ViewHolder pattern
	static class ItemHolder {
		ImageView tagView;
		TextView nameView;
		TextView descrView;
		TextView dateView;		
	}

}
