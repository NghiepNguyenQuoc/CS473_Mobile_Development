package nghiepnguyen.com.phrasebook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.adapter.CustomListViewAdapter;
import nghiepnguyen.com.phrasebook.model.Category;
import nghiepnguyen.com.phrasebook.model.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    ListView listView;
    List<Category> rowItems;

    public static final String VALUE_CATEGORY = "";

    String[] actions = new String[]{"Vietnamese", "Chinese", "Japanese",
            "Korea",};
    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        /*ArrayAdapter<String> adaptermenu = new ArrayAdapter<String>(
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
                navigationListener);*/


        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DATABASE_NAME);
        if (database.exists() == false) {
            databaseHelper.getReadableDatabase();
            copyDatabase();
        }

        rowItems = new ArrayList<Category>();
        rowItems = databaseHelper.GetAllCategory();

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

    public void copyDatabase() {
        try {
            InputStream inputStream = getAssets().open(DatabaseHelper.DATABASE_NAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int lenght = 0;
            while ((lenght = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, lenght);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
