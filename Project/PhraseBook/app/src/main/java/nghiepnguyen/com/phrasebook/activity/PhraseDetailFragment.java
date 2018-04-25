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

import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.adapter.ExpandableListAdapter;
import nghiepnguyen.com.phrasebook.common.Constants;
import nghiepnguyen.com.phrasebook.common.DatabaseHelper;
import nghiepnguyen.com.phrasebook.common.IExpandListViewExpanded;
import nghiepnguyen.com.phrasebook.common.ILongClickItem;
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
    private View emptyView;
    private int valueCategory;
    private DatabaseHelper databaseHelper;
    private String databaseName;
    private String language;
    private int isLock;
    private boolean isEmpty = false;

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
        databaseName = getArguments().getString(Constants.INSTANCE.getBUNDLE_DATABASE());
        language = getArguments().getString(Constants.INSTANCE.getBUNDLE_LANGUAGE());
        valueCategory = getArguments().getInt(Constants.INSTANCE.getBUNDLE_CATEGORY());
        isLock = getArguments().getInt(Constants.INSTANCE.getBUNDLE_LOCK());
        databaseHelper = new DatabaseHelper(mContext, databaseName);

        View view = inflater.inflate(R.layout.fragment_phrase, container, false);
        expListView = view.findViewById(R.id.phrase_expandable_listview);
        emptyView = view.findViewById(R.id.empty_view);

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

    public void createCollection() {
        phraseCollection = new LinkedHashMap<>();
        for (Phrase item : phraseList) {
            List<Phrase> childList = new ArrayList<>();
            childList.add(item);
            phraseCollection.put(item, childList);
        }
    }

    public void fillDataToListView() {
        if (phraseList.size() > 0) {
            ExpandableListAdapter expListAdapter = new ExpandableListAdapter(mContext, phraseList, phraseCollection, databaseName, isLock, new IExpandListViewExpanded() {
                @Override
                public void onGroupExpanded(int position) {
                    expListView.collapseGroup(position);
                }
            }, new ILongClickItem() {
                @Override
                public void onLongClickItem(Phrase item) {
                    String str = "I have learned " + language + " phrase \nPhrase: "
                            + item.getPhrase() + "\nPronuciation: " + item.getPronunciation() + "\nMean: " + item.getMean();
                    ;
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Let learn " + language + "with me!!!!!!");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, str);
                    sendIntent.setType("text/plain");
                    startActivity(Intent.createChooser(sendIntent, getResources()
                            .getText(R.string.app_name)));

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

        }
    }

    public void onQueryTextChange(String newText) {
        List<Phrase> listTemp = databaseHelper.GetAllPhraseOfCategoryByName(
                valueCategory, newText);
        if (listTemp == null || listTemp.size() == 0) {
            emptyView.setVisibility(View.VISIBLE);
            expListView.setVisibility(View.GONE);
            isEmpty = true;
        } else {
            isEmpty = false;
            phraseList = new ArrayList<>();
            emptyView.setVisibility(View.GONE);
            expListView.setVisibility(View.VISIBLE);
            phraseList.addAll(listTemp);
            createCollection();
            fillDataToListView();
        }
    }

    public void onSeletedFavorite() {
        List<Phrase> listTemp = databaseHelper.GetAllPhraseFavoriteOfCategory();
        if (listTemp == null || listTemp.size() == 0) {
            emptyView.setVisibility(View.VISIBLE);
            expListView.setVisibility(View.GONE);
            isEmpty = true;
        } else {
            isEmpty = false;
            phraseList = new ArrayList<>();
            emptyView.setVisibility(View.GONE);
            expListView.setVisibility(View.VISIBLE);
            phraseList.addAll(listTemp);
            createCollection();
            fillDataToListView();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.INSTANCE.getVOICE_RECOGNITION_REQUEST_CODE()
                && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            onQueryTextChange(matches.get(0));
            Toast.makeText(mContext, "You said:\" " + matches.get(0) + " \"",
                    Toast.LENGTH_LONG).show();
        }
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
