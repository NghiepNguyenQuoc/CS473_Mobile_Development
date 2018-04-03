package nghiepnguyen.com.phrasebook.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
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

import nghiepnguyen.com.phrasebook.MainActivity;
import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.adapter.ExpandableListAdapter;
import nghiepnguyen.com.phrasebook.model.Category;
import nghiepnguyen.com.phrasebook.model.CategoryDAO;
import nghiepnguyen.com.phrasebook.model.DatabaseHelper;
import nghiepnguyen.com.phrasebook.model.Phrase;
import nghiepnguyen.com.phrasebook.model.PhraseDAO;

public class PhraseActivity extends Activity implements
        SearchView.OnQueryTextListener {
    List<Phrase> phraseList;// Danh sach cac danh muc cha
    List<Phrase> childList;// Danh sach cac danh muc con cua cha
    Map<Phrase, List<Phrase>> laptopCollection;
    ExpandableListView expListView;// Listview mo rong

    int valueCategory;
    PhraseDAO phraseDAO = null;
    CategoryDAO categoryDAO = null;
    private DatabaseHelper helper;

    private SearchView mSearchView;

    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase);

        Bundle extras = getIntent().getExtras();
        valueCategory = extras.getInt(MainActivity.VALUE_CATEGORY);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        helper = new DatabaseHelper(this, "data", null, 1);
        this.phraseDAO = new PhraseDAO(null, null, null, 1);
        this.categoryDAO = new CategoryDAO(null, null, null, 1);

        createGroupList();
        createCollection();

        fillDataToListView();
        SpeechToText();
        actionBar.setTitle(GetNameCategoryById(valueCategory));
    }

    private void createGroupList() {
        helper.open();
        phraseList = new ArrayList<Phrase>();
        List<Phrase> listTemp = phraseDAO.GetAllPhraseOfCategory(valueCategory);
        for (Phrase item : listTemp) {
            phraseList.add(item);
        }
        phraseDAO.close();
    }

    private void createCollection() {
        laptopCollection = new LinkedHashMap<Phrase, List<Phrase>>();
        for (Phrase item : phraseList) {
            childList = new ArrayList<Phrase>();
            childList.add(item);
            laptopCollection.put(item, childList);
        }
    }

    private String GetNameCategoryById(int id) {
        helper.open();
        Category category = categoryDAO.getNameById(id);
        helper.close();
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
            expListView = (ExpandableListView) findViewById(R.id.laptop_list);
            final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                    this, phraseList, laptopCollection, onlongclick);
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

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            // | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        }
        mSearchView.setOnQueryTextListener(this);
    }

    public boolean onQueryTextChange(String newText) {
        // Toast.makeText(this, newText, Toast.LENGTH_SHORT).show();
        helper.open();
        phraseList = new ArrayList<Phrase>();
        List<Phrase> listTemp = phraseDAO.GetAllPhraseOfCategoryByName(
                valueCategory, newText);
        for (Phrase item : listTemp) {
            phraseList.add(item);
        }
        phraseDAO.close();
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
                helper.open();
                phraseList = new ArrayList<Phrase>();
                List<Phrase> listTemp = phraseDAO.GetAllPhraseFavoriteOfCategory();
                for (Phrase item1 : listTemp) {
                    phraseList.add(item1);
                }
                phraseDAO.close();
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
        // TODO Auto-generated method stub
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Speech recognition demo");
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    private void SpeechToText() {
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE
                && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            onQueryTextChange(matches.get(0));
            Toast.makeText(this, "You said:\" " + matches.get(0) + " \"",
                    Toast.LENGTH_LONG).show();

            helper.open();
            phraseList = new ArrayList<Phrase>();
            List<Phrase> listTemp = phraseDAO.GetAllPhraseOfCategoryByName(
                    valueCategory, matches.get(0));
            for (Phrase item : listTemp) {
                phraseList.add(item);
            }
            phraseDAO.close();
            createCollection();
            fillDataToListView();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
