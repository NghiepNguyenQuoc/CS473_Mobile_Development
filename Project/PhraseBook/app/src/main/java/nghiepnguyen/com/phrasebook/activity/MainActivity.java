package nghiepnguyen.com.phrasebook.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.model.Constants;

public class MainActivity extends AppCompatActivity {
    public static final String VALUE_CATEGORY = "VALUE_CATEGORY";
    public Fragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new PhraseFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_DATABASE, Constants.DATABASE_VN_NAME);
        fragment.setArguments(bundle);
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
        Bundle bundle = new Bundle();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.vietnamese_item:
                bundle.putString(Constants.BUNDLE_DATABASE, Constants.DATABASE_VN_NAME);
                break;
            case R.id.chinese_item:
                bundle.putString(Constants.BUNDLE_DATABASE, Constants.DATABASE_TQ_NAME);
                break;
            case R.id.japanese_item:
                bundle.putString(Constants.BUNDLE_DATABASE, Constants.DATABASE_NB_NAME);
                break;
            case R.id.korean_item:
                bundle.putString(Constants.BUNDLE_DATABASE, Constants.DATABASE_HQ_NAME);
                break;
        }
        fragment.setArguments(bundle);
        fragment = getSupportFragmentManager().findFragmentByTag("fragment");
        ft.detach(fragment);
        ft.attach(fragment);
        ft.commit();
        return true;
    }
}
