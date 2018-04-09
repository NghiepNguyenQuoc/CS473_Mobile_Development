package com.swa.todo;

import com.swa.todo.fragment.NewItemFragment;
import com.swa.todo.model.Item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class NewItemActivity extends Activity implements NewItemFragment.AddItemListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		NewItemFragment nif = new NewItemFragment();
		
		getFragmentManager().beginTransaction().add(android.R.id.content, nif).commit();
	}

	@Override
	public void onAddItem(Item item) {
		// We get the item and return to the main activity
		Log.d("TODO", "onAddItem");
		Intent i = new Intent();
		i.putExtra("item", item);
		setResult(RESULT_OK,i);
		finish();
		
	}
	

}
