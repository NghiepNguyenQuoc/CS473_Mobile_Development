package nghiepnguyen.com.phrasebook.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nghiepnguyen.com.phrasebook.IExpandListViewExpanded;
import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.adapter.ExpandableListAdapter;
import nghiepnguyen.com.phrasebook.model.Constants;
import nghiepnguyen.com.phrasebook.model.DatabaseHelper;
import nghiepnguyen.com.phrasebook.model.Phrase;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhraseDetailFragment extends Fragment {
    private Context mContext;
    private List<Phrase> phraseList;
    private Map<Phrase, List<Phrase>> phraseCollection;
    private ExpandableListView expListView;
    private int valueCategory;
    private DatabaseHelper databaseHelper;
    private String databaseName;
    private int isLock;

    public PhraseDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        databaseName = getArguments().getString(Constants.BUNDLE_DATABASE);
        valueCategory = getArguments().getInt(MainActivity.VALUE_CATEGORY);
        isLock = getArguments().getInt(Constants.BUNDLE_LOCK);
        databaseHelper = new DatabaseHelper(mContext, databaseName);

        View view = inflater.inflate(R.layout.fragment_phrase, container, false);
        expListView = view.findViewById(R.id.phrase_expandable_listview);

        createGroupList();
        createCollection();
        fillDataToListView();
        return view;
    }

    private void createGroupList() {
        phraseList = new ArrayList<>();
        List<Phrase> listTemp = databaseHelper.GetAllPhraseOfCategory(valueCategory);
        phraseList.addAll(listTemp);
    }

    private void createCollection() {
        phraseCollection = new LinkedHashMap<>();
        for (Phrase item : phraseList) {
            List<Phrase> childList = new ArrayList<>();
            childList.add(item);
            phraseCollection.put(item, childList);
        }
    }

    private void fillDataToListView() {
        if (phraseList.size() > 0) {
            ExpandableListAdapter expListAdapter = new ExpandableListAdapter(mContext, phraseList, phraseCollection, onlongclick, databaseName, isLock, new IExpandListViewExpanded() {
                @Override
                public void onGroupExpanded(int position) {
                    expListView.collapseGroup(position);
                }
            });
            expListView.setAdapter(expListAdapter);
            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    v.setSelected(true);
                    return true;
                }
            });
            expListAdapter.notifyDataSetChanged();

        } else {
            //setContentView(R.layout.activity_null);
        }
    }

    public void onQueryTextChange(String newText) {
        phraseList = new ArrayList<>();
        List<Phrase> listTemp = databaseHelper.GetAllPhraseOfCategoryByName(
                valueCategory, newText);
        phraseList.addAll(listTemp);
        createCollection();
        fillDataToListView();
    }

    public void onSeletedFavorite() {
        phraseList = new ArrayList<>();
        List<Phrase> listTemp = databaseHelper.GetAllPhraseFavoriteOfCategory();
        phraseList.addAll(listTemp);
        createCollection();
        fillDataToListView();
    }

    View.OnLongClickListener onlongclick = new View.OnLongClickListener() {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.VOICE_RECOGNITION_REQUEST_CODE
                && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            onQueryTextChange(matches.get(0));
            Toast.makeText(mContext, "You said:\" " + matches.get(0) + " \"",
                    Toast.LENGTH_LONG).show();
        }
    }
}
