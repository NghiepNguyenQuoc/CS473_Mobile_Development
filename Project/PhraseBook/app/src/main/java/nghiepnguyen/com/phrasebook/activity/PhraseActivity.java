package nghiepnguyen.com.phrasebook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.model.Category;
import nghiepnguyen.com.phrasebook.model.Constants;
import nghiepnguyen.com.phrasebook.model.DatabaseHelper;

public class PhraseActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    int valueCategory;
    DatabaseHelper databaseHelper;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        Bundle extras = getIntent().getExtras();
        String databaseName = extras.getString(Constants.BUNDLE_DATABASE);
        databaseHelper = new DatabaseHelper(this, databaseName);
        Category category = databaseHelper.getNameById(valueCategory);
        actionBar.setTitle(category.getNamecategory());

        Fragment fragment = new PhraseDetailFragment();
        fragment.setArguments(extras);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_phrase, fragment, "fragment")
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.phrase_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        mSearchView = (SearchView) searchItem.getActionView();
        setupSearchView(searchItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
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

    private void setupSearchView(MenuItem searchItem) {
        if (isAlwaysExpanded()) {
            mSearchView.setIconifiedByDefault(false);
        } else {
            mSearchView.setQueryHint(getString(R.string.search_hint));
            searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        mSearchView.setOnQueryTextListener(this);
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

    public boolean onQueryTextSubmit(String query) {
        // Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        return false;
    }

    protected boolean isAlwaysExpanded() {
        return false;
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
}
