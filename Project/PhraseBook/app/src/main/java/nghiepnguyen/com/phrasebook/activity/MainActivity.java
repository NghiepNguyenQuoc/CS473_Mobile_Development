package nghiepnguyen.com.phrasebook.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.adapter.CategoryAdapter;
import nghiepnguyen.com.phrasebook.model.Category;
import nghiepnguyen.com.phrasebook.model.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    public static final String VALUE_CATEGORY="VALUE_CATEGORY";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DATABASE_NAME);
        if (!database.exists()) {
            databaseHelper.getReadableDatabase();
            copyDatabase();
        }

        List<Category> rowItems = databaseHelper.GetAllCategory();
        ListView listView = findViewById(R.id.list);
        CategoryAdapter adapter = new CategoryAdapter(this, rowItems);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        PackageManager pm = getPackageManager();
        Intent intent;
        switch (item.getItemId()) {
            case R.id.vietnamese_item:
                break;
            case R.id.chinese_item:
                intent = pm.getLaunchIntentForPackage("com.example.phrasebooktq");
                startActivity(intent);
                break;
            case R.id.japanese_item:
                intent = pm.getLaunchIntentForPackage("com.example.phrasebookjp");
                startActivity(intent);
                break;
            case R.id.korean_item:
                intent = pm.getLaunchIntentForPackage("com.example.phrasebookhq");
                startActivity(intent);
                break;
        }
        return true;
    }

    public void copyDatabase() {
        try {
            InputStream inputStream = getAssets().open(DatabaseHelper.DATABASE_NAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int lenght;
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
