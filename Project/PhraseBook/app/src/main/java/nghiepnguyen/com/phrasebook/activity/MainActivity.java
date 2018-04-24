package nghiepnguyen.com.phrasebook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.adapter.CategoryAdapter;
import nghiepnguyen.com.phrasebook.common.Constants;
import nghiepnguyen.com.phrasebook.common.DatabaseHelper;
import nghiepnguyen.com.phrasebook.model.Category;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static final String VALUE_CATEGORY = "VALUE_CATEGORY";
    private boolean mTwoPane;
    private RecyclerView mRecyclerView;
    private CategoryAdapter adapter;
    private SearchView mSearchView;
    private String currentDB = null;
    private Toolbar toolbar;
    private ImageView mHeaderImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }

        toolbar = findViewById(R.id.toolbar);
        mHeaderImageView = findViewById(R.id.header_imageview);
        setSupportActionBar(toolbar);
        mRecyclerView = findViewById(R.id.item_list);
        assert mRecyclerView != null;
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setupRecyclerView(Constants.DATABASE_VN_NAME, mTwoPane);
        mHeaderImageView.setImageResource(R.drawable.vietnam_landscape);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mTwoPane) {
            getMenuInflater().inflate(R.menu.main_menu_2, menu);
            MenuItem searchItem = menu.findItem(R.id.search);
            mSearchView = (SearchView) searchItem.getActionView();
            setupSearchView(searchItem);
        } else {
            getMenuInflater().inflate(R.menu.main_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.vietnamese_item:
                setupRecyclerView(Constants.DATABASE_VN_NAME, mTwoPane);
                mHeaderImageView.setImageResource(R.drawable.vietnam_landscape);
                break;
            case R.id.chinese_item:
                setupRecyclerView(Constants.DATABASE_TQ_NAME, mTwoPane);
                mHeaderImageView.setImageResource(R.drawable.china_header);
                break;
            case R.id.japanese_item:
                setupRecyclerView(Constants.DATABASE_NB_NAME, mTwoPane);
                mHeaderImageView.setImageResource(R.drawable.japan_landscape);

                break;
            case R.id.korean_item:
                setupRecyclerView(Constants.DATABASE_HQ_NAME, mTwoPane);
                mHeaderImageView.setImageResource(R.drawable.korea_header);

                break;

            case R.id.search:
                break;
            case R.id.voice:
                startVoiceRecognitionActivity();
                break;
            case R.id.favorite:
                onOpenFavorite();
                break;
        }
        return true;
    }

    private void setupRecyclerView(String databaseName, boolean mTwoPane) {
        currentDB = databaseName;
        DatabaseHelper databaseHelper = new DatabaseHelper(this, databaseName);
        File database = getDatabasePath(databaseName);
        if (!database.exists()) {
            databaseHelper.getReadableDatabase();
            copyDatabase();
        }

        List<Category> rowItems = databaseHelper.GetAllCategory();
        adapter = new CategoryAdapter(this, rowItems, databaseName, mTwoPane);
        mRecyclerView.setAdapter(adapter);
    }

    private void setupSearchView(MenuItem searchItem) {
        if (isAlwaysExpanded()) {
            mSearchView.setIconifiedByDefault(false);
        } else {
            mSearchView.setQueryHint(getString(R.string.search_hint));
            searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        mSearchView.setOnQueryTextListener(this);
    }

    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.talk_to_search));
        startActivityForResult(intent, Constants.VOICE_RECOGNITION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhraseDetailFragment myFragment = (PhraseDetailFragment) getSupportFragmentManager().findFragmentByTag("fragment");
        if (myFragment != null && myFragment.isVisible()) {
            myFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    protected boolean isAlwaysExpanded() {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        PhraseDetailFragment myFragment = (PhraseDetailFragment) getSupportFragmentManager().findFragmentByTag("fragment");
        if (myFragment != null && myFragment.isVisible()) {
            myFragment.onQueryTextChange(newText);
        }
        return false;
    }

    public void onOpenFavorite() {
        PhraseDetailFragment myFragment = (PhraseDetailFragment) getSupportFragmentManager().findFragmentByTag("fragment");
        if (myFragment != null && myFragment.isVisible()) {
            myFragment.onSeletedFavorite();
        }
    }

    public void copyDatabase() {
        try {
            InputStream inputStream = getAssets().open(currentDB);
            String outFileName = Constants.DBLOCATION + currentDB;
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
