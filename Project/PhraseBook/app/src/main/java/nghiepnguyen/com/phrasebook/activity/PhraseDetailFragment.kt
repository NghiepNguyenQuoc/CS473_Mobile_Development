package nghiepnguyen.com.phrasebook.activity

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.Toast
import nghiepnguyen.com.phrasebook.R
import nghiepnguyen.com.phrasebook.adapter.ExpandableListAdapter
import nghiepnguyen.com.phrasebook.common.Constants
import nghiepnguyen.com.phrasebook.common.DatabaseHelper
import nghiepnguyen.com.phrasebook.common.IExpandListViewExpanded
import nghiepnguyen.com.phrasebook.common.ILongClickItem
import nghiepnguyen.com.phrasebook.model.Phrase
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class PhraseDetailFragment : Fragment() {
    private var mContext: Context? = null
    private var phraseList: MutableList<Phrase>? = null
    private var phraseCollection: MutableMap<Phrase, List<Phrase>>? = null
    private var expListView: ExpandableListView? = null
    private var emptyView: View? = null
    private var valueCategory: Int = 0
    private var databaseHelper: DatabaseHelper? = null
    private var databaseName: String? = null
    private var language: String? = null
    private var isLock: Int = 0
    var isEmpty = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        databaseName = arguments!!.getString(Constants.BUNDLE_DATABASE)
        language = arguments!!.getString(Constants.BUNDLE_LANGUAGE)
        valueCategory = arguments!!.getInt(Constants.BUNDLE_CATEGORY)
        isLock = arguments!!.getInt(Constants.BUNDLE_LOCK)
        databaseHelper = DatabaseHelper(mContext!!, databaseName!!)

        val view = inflater.inflate(R.layout.fragment_phrase, container, false)
        expListView = view.findViewById(R.id.phrase_expandable_listview)
        emptyView = view.findViewById(R.id.empty_view)

        createGroupList()
        createCollection()
        fillDataToListView()
        return view
    }

    private fun createGroupList() {
        phraseList = ArrayList()
        val listTemp = databaseHelper!!.getAllPhraseOfCategory(valueCategory)
        phraseList!!.addAll(listTemp)
    }

    fun createCollection() {
        phraseCollection = LinkedHashMap()
        for (item in phraseList!!) {
            val childList = ArrayList<Phrase>()
            childList.add(item)
            phraseCollection!![item] = childList
        }
    }

    fun fillDataToListView() {
        if (phraseList!!.size > 0) {
            val expListAdapter = ExpandableListAdapter(mContext!!, phraseList!!, phraseCollection!!, databaseName!!, isLock, object : IExpandListViewExpanded {
                override fun onGroupExpanded(position: Int) {
                    expListView!!.collapseGroup(position)
                }
            }, object : ILongClickItem {
                override fun onLongClickItem(item: Phrase) {
                    val str = ("I have learned " + language + " phrase \nPhrase: "
                            + item.phrase + "\nPronuciation: " + item.pronunciation + "\nMean: " + item.mean)
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Let learn " + language + "with me!!!!!!")
                    sendIntent.putExtra(Intent.EXTRA_TEXT, str)
                    sendIntent.type = "text/plain"
                    startActivity(Intent.createChooser(sendIntent, resources
                            .getText(R.string.app_name)))

                }
            })
            expListView!!.setAdapter(expListAdapter)
            expListAdapter.notifyDataSetChanged()

        }
    }

    fun onQueryTextChange(newText: String) {
        val listTemp = databaseHelper!!.getAllPhraseOfCategoryByName(
                valueCategory, newText)
        if (listTemp.size == 0) {
            emptyView!!.visibility = View.VISIBLE
            expListView!!.visibility = View.GONE
            isEmpty = true
        } else {
            isEmpty = false
            phraseList = ArrayList()
            emptyView!!.visibility = View.GONE
            expListView!!.visibility = View.VISIBLE
            phraseList!!.addAll(listTemp)
            createCollection()
            fillDataToListView()
        }
    }

    fun onSeletedFavorite() {
        val listTemp = databaseHelper!!.getAllPhraseFavoriteOfCategory()
        if (listTemp.size == 0) {
            emptyView!!.visibility = View.VISIBLE
            expListView!!.visibility = View.GONE
            isEmpty = true
        } else {
            isEmpty = false
            phraseList = ArrayList()
            emptyView!!.visibility = View.GONE
            expListView!!.visibility = View.VISIBLE
            phraseList!!.addAll(listTemp)
            createCollection()
            fillDataToListView()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            val matches = data!!
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            onQueryTextChange(matches[0])
            Toast.makeText(mContext, "You said:\" " + matches[0] + " \"",
                    Toast.LENGTH_LONG).show()
        }
    }
}// Required empty public constructor
