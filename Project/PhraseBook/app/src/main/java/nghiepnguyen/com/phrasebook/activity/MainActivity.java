package nghiepnguyen.com.phrasebook;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import nghiepnguyen.com.phrasebook.activity.PhraseActivity;
import nghiepnguyen.com.phrasebook.adapter.CustomListViewAdapter;
import nghiepnguyen.com.phrasebook.model.Category;
import nghiepnguyen.com.phrasebook.model.CategoryDAO;
import nghiepnguyen.com.phrasebook.model.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    ListView listView;
    List<Category> rowItems;
    CategoryDAO categoryDAO = null;
    private DatabaseHelper helper;

    public static final String VALUE_CATEGORY = "";

    String[] actions = new String[]{"Vietnamese", "Chinese", "Japanese",
            "Korea",};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adaptermenu = new ArrayAdapter<String>(
                getBaseContext(), R.layout.drop_item, actions);
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getActionBar().setTitle("");
        ActionBar.OnNavigationListener navigationListener = new OnNavigationListener() {

            @Override
            public boolean onNavigationItemSelected(int itemPosition,
                                                    long itemId) {
                if (itemPosition == 0) {
                }
                if (itemPosition == 1) {
                    PackageManager pm = getPackageManager();
                    Intent intent = pm
                            .getLaunchIntentForPackage("com.example.phrasebooktq");
                    startActivity(intent);
                }
                if (itemPosition == 2) {
                    PackageManager pm = getPackageManager();
                    Intent intent = pm
                            .getLaunchIntentForPackage("com.example.phrasebookjp");
                    startActivity(intent);
                }
                if (itemPosition == 3) {
                    PackageManager pm = getPackageManager();
                    Intent intent = pm
                            .getLaunchIntentForPackage("com.example.phrasebookhq");
                    startActivity(intent);
                }
                return false;
            }
        };

        getActionBar().setListNavigationCallbacks(adaptermenu,
                navigationListener);

        helper = new DatabaseHelper(this, "data", null, 1);
        helper.open();
        this.categoryDAO = new CategoryDAO(null, null, null, 1);

        rowItems = new ArrayList<Category>();
        rowItems = categoryDAO.GetAllCategory();
        helper.close();

        listView = (ListView) findViewById(R.id.list);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.list_item, rowItems);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Intent intent = new Intent(this, PhraseActivity.class);
        intent.putExtra(VALUE_CATEGORY, rowItems.get(position).getId()); // Your
        // id
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        return super.onCreateOptionsMenu(menu);
    }
}
