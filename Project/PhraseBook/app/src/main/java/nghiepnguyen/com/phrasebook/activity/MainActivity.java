package nghiepnguyen.com.phrasebook.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import nghiepnguyen.com.phrasebook.R;

public class MainActivity extends AppCompatActivity {
    public static final String VALUE_CATEGORY = "VALUE_CATEGORY";
    public Fragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new PhraseFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment, "fragment").commit();
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
}
