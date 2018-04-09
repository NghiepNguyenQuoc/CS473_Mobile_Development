package nghiepnguyen.com.phrasebook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.adapter.ExpandableListAdapter;
import nghiepnguyen.com.phrasebook.model.Category;
import nghiepnguyen.com.phrasebook.model.Constants;
import nghiepnguyen.com.phrasebook.model.DatabaseHelper;
import nghiepnguyen.com.phrasebook.model.Phrase;

public class PhraseActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    List<Phrase> phraseList;
    List<Phrase> childList;
    Map<Phrase, List<Phrase>> laptopCollection;
    ExpandableListView expListView;
    int valueCategory;
    DatabaseHelper databaseHelper;
    private SearchView mSearchView;
    private String databaseName;
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase);

        Bundle extras = getIntent().getExtras();
        valueCategory = extras.getInt(MainActivity.VALUE_CATEGORY);
        databaseName = extras.getString(Constants.BUNDLE_DATABASE);
        databaseHelper = new DatabaseHelper(this, databaseName);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        createGroupList();
        createCollection();

        fillDataToListView();
        actionBar.setTitle(GetNameCategoryById(valueCategory));
    }

    private void createGroupList() {
        phraseList = new ArrayList<>();
        List<Phrase> listTemp = databaseHelper.GetAllPhraseOfCategory(valueCategory);
        phraseList.addAll(listTemp);
    }

    private void createCollection() {
        laptopCollection = new LinkedHashMap<>();
        for (Phrase item : phraseList) {
            childList = new ArrayList<>();
            childList.add(item);
            laptopCollection.put(item, childList);
        }
    }

    private String GetNameCategoryById(int id) {
        Category category = databaseHelper.getNameById(id);
        return category.getNamecategory();
    }

    OnLongClickListener onlongclick = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            String str = v.getTag().toString();

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, str);
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources()
                    .getText(R.string.app_name)));

            return true;
        }
    };

    private void fillDataToListView() {
        if (phraseList.size() > 0) {
            setContentView(R.layout.activity_phrase);
            expListView = findViewById(R.id.laptop_list);
            final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(this, phraseList, laptopCollection, onlongclick,databaseName);
            expListView.setAdapter(expListAdapter);
            expListView.setOnChildClickListener(new OnChildClickListener() {
                public boolean onChildClick(ExpandableListView parent, View v,
                                            int groupPosition, int childPosition, long id) {
                    v.setSelected(true);
                    return true;
                }
            });
            expListAdapter.notifyDataSetChanged();

        } else {
            setContentView(R.layout.activity_null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.phrase_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        mSearchView = (SearchView) searchItem.getActionView();
        setupSearchView(searchItem);
        return true;
    }

    private void setupSearchView(MenuItem searchItem) {
        if (isAlwaysExpanded()) {
            mSearchView.setIconifiedByDefault(false);
        } else {
            mSearchView.setQueryHint("Search for phrase");
            searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        mSearchView.setOnQueryTextListener(this);
    }

    public boolean onQueryTextChange(String newText) {
        phraseList = new ArrayList<>();
        List<Phrase> listTemp = databaseHelper.GetAllPhraseOfCategoryByName(
                valueCategory, newText);
        phraseList.addAll(listTemp);
        createCollection();
        fillDataToListView();
        return false;
    }

    public boolean onQueryTextSubmit(String query) {
        // Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        return false;
    }

    protected boolean isAlwaysExpanded() {
        return false;
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
                phraseList = new ArrayList<>();
                List<Phrase> listTemp = databaseHelper.GetAllPhraseFavoriteOfCategory();
                phraseList.addAll(listTemp);
                createCollection();
                fillDataToListView();
                break;
            default:
                onBackPressed();
                break;
        }
        return true;
    }

    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Speech recognition demo");
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE
                && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            onQueryTextChange(matches.get(0));
            Toast.makeText(this, "You said:\" " + matches.get(0) + " \"",
                    Toast.LENGTH_LONG).show();

            phraseList = new ArrayList<>();
            List<Phrase> listTemp = databaseHelper.GetAllPhraseOfCategoryByName(
                    valueCategory, matches.get(0));
            phraseList.addAll(listTemp);
            createCollection();
            fillDataToListView();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
