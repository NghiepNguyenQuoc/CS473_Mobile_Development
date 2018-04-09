package com.example.rmohanraj.actionbarmenutabdemo;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar aBar=getSupportActionBar();
        aBar.setTitle("Social Media");
        aBar.setBackgroundDrawable(new ColorDrawable(Color.GREEN));
       /* aBar.setBackgroundDrawable(getResources().
                getDrawable(R.drawable.abg));*/
        aBar.setIcon(getResources().getDrawable(
                R.drawable.twitter_logo));
        aBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
// TabListner definition
        ActionBar.TabListener listener=new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                Toast.makeText(getApplicationContext(),tab.getText(),
                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {}

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}
        };
       
// Adding tab and TabListener to the ActionBar
aBar.addTab(aBar.newTab().setText("Java").setTabListener(listener));
        aBar.addTab(aBar.newTab().setText("Android").setTabListener(listener));
        aBar.addTab(aBar.newTab().setText("iOS").setTabListener(listener));
        aBar.addTab(aBar.newTab().setText(".Net").setTabListener(listener));
        aBar.addTab(aBar.newTab().setText("Hadoop").setTabListener(listener));
        aBar.addTab(aBar.newTab().setText("PHP").setTabListener(listener));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test,menu);
        // How make use of SearchView
        SearchView sv = (SearchView) menu.findItem(R.id.searchbar).getActionView(); //returns item's action view
        // Whatever you typed to search the content, will be received using SearchManager object
        SearchManager sm = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        sv.setSearchableInfo(sm.getSearchableInfo(getComponentName()));
        // To get the typed query use setOnQueryTextListener()
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // Use this method to retrieve the typed query after clicking the Search
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(),query,Toast.LENGTH_LONG).show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getApplicationContext(),
                item.getTitle().toString(),
                Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }
}

