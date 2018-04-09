package com.swa.todo;

import java.util.ArrayList;
import java.util.List;

import com.swa.todo.fragment.NewItemFragment;
import com.swa.todo.model.Item;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity implements NewItemFragment.AddItemListener{

	private List<Item> itemList = new ArrayList<Item>();

	private static int ADD_ITEM = 100;
	ListView itemListView;
	ToDoItemAdapter adpt;
	private boolean isTablet = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		itemListView = (ListView) findViewById(R.id.listItmes);

		adpt = new ToDoItemAdapter(this, itemList);

		itemListView.setAdapter(adpt);

		// We check if the app runs in a tablet
		if (findViewById(R.id.frm1) != null) 
			isTablet = true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int menuId = item.getItemId();

		switch (menuId) {
			case R.id.action_add: {
				if (!isTablet) {
					Intent i = new Intent(this, NewItemActivity.class);
					startActivityForResult(i, ADD_ITEM);
					break;
				}
				else {
					Log.d("TODO", "Tablet");
					FragmentTransaction ft = getFragmentManager().beginTransaction();
					NewItemFragment nif = new NewItemFragment();
					ft.replace(R.id.frm1, nif);
					ft.commit();
				}
			}
		}

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("TODO", "OnResult");
		if (requestCode == ADD_ITEM) {
			if (resultCode == RESULT_OK) {
				Log.d("TODO", "OK");
				Item i = (Item) data.getExtras().getSerializable("item");
				itemList.add(i);
				adpt.notifyDataSetChanged();
			}
		}
	}

	@Override
	public void onAddItem(Item item) {
		itemList.add(item);
		adpt.notifyDataSetChanged();
		NewItemFragment nif = (NewItemFragment) getFragmentManager().findFragmentById(R.id.frm1);
		getFragmentManager().beginTransaction().remove(nif).commit();
		
	}
	
	

}
