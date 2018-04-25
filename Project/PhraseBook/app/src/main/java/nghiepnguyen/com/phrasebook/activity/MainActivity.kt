package nghiepnguyen.com.phrasebook.activity

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import nghiepnguyen.com.phrasebook.R
import nghiepnguyen.com.phrasebook.adapter.CategoryAdapter
import nghiepnguyen.com.phrasebook.common.Constants
import nghiepnguyen.com.phrasebook.common.DatabaseHelper
import java.io.FileOutputStream
import java.io.IOException

open class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private var mTwoPane: Boolean = false
    private var mRecyclerView: RecyclerView? = null
    private var adapter: CategoryAdapter? = null
    private var mSearchView: SearchView? = null
    private var currentDB: String? = null
    private var mHeaderImageView: ImageView? = null

    private val isAlwaysExpanded: Boolean
        get() = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (findViewById<View>(R.id.item_detail_container) != null) {
            mTwoPane = true
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        mHeaderImageView = findViewById(R.id.header_imageview)
        mRecyclerView = findViewById(R.id.item_list)
        assert(mRecyclerView != null)
        mRecyclerView!!.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView!!.layoutManager = mLayoutManager
        setupRecyclerView(Constants.DATABASE_VN_NAME, getString(R.string.language_vietnamese), mTwoPane)
        mHeaderImageView!!.setImageResource(R.drawable.vietnam_landscape)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (mTwoPane) {
            menuInflater.inflate(R.menu.main_menu_2, menu)
            val searchItem = menu.findItem(R.id.search)
            mSearchView = searchItem.actionView as SearchView
            setupSearchView(searchItem)
        } else {
            menuInflater.inflate(R.menu.main_menu, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.vietnamese_item -> {
                setupRecyclerView(Constants.DATABASE_VN_NAME, getString(R.string.language_vietnamese), mTwoPane)
                mHeaderImageView!!.setImageResource(R.drawable.vietnam_landscape)
            }
            R.id.chinese_item -> {
                setupRecyclerView(Constants.DATABASE_TQ_NAME, getString(R.string.language_chinese), mTwoPane)
                mHeaderImageView!!.setImageResource(R.drawable.china_header)
            }
            R.id.japanese_item -> {
                setupRecyclerView(Constants.DATABASE_NB_NAME, getString(R.string.language_japanese), mTwoPane)
                mHeaderImageView!!.setImageResource(R.drawable.japan_landscape)
            }
            R.id.korean_item -> {
                setupRecyclerView(Constants.DATABASE_HQ_NAME, getString(R.string.language_korean), mTwoPane)
                mHeaderImageView!!.setImageResource(R.drawable.korea_header)
            }
            R.id.search -> {
            }
            R.id.voice -> startVoiceRecognitionActivity()
            R.id.favorite -> onOpenFavorite()
        }
        return true
    }

    private fun setupRecyclerView(databaseName: String, language: String, mTwoPane: Boolean) {
        currentDB = databaseName
        val databaseHelper = DatabaseHelper(this, databaseName)
        val database = getDatabasePath(databaseName)
        if (!database.exists()) {
            databaseHelper.readableDatabase
            copyDatabase()
        }

        val rowItems = databaseHelper.getAllCategory()
        adapter = CategoryAdapter(this, rowItems, databaseName, language, mTwoPane)
        mRecyclerView!!.adapter = adapter
    }

    private fun setupSearchView(searchItem: MenuItem) {
        if (isAlwaysExpanded) {
            mSearchView!!.setIconifiedByDefault(false)
        } else {
            mSearchView!!.queryHint = getString(R.string.search_hint)
            searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        }
        mSearchView!!.setOnQueryTextListener(this)
    }

    private fun startVoiceRecognitionActivity() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.talk_to_search))
        startActivityForResult(intent, Constants.VOICE_RECOGNITION_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        val myFragment = supportFragmentManager.findFragmentByTag("fragment") as PhraseDetailFragment
        if (myFragment.isVisible) {
            myFragment.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onQueryTextSubmit(s: String): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        val myFragment = supportFragmentManager.findFragmentByTag("fragment") as PhraseDetailFragment
        if (myFragment.isVisible) {
            myFragment.onQueryTextChange(newText)
        }
        return false
    }

    private fun onOpenFavorite() {
        val myFragment = supportFragmentManager.findFragmentByTag("fragment") as PhraseDetailFragment
        if (myFragment.isVisible) {
            myFragment.onSeletedFavorite()
        }
    }

    private fun copyDatabase() {
        try {
            val inputStream = assets.open(currentDB)
            val outFileName = Constants.DBLOCATION + currentDB!!
            val outputStream = FileOutputStream(outFileName)
            val buff = ByteArray(1024)
            var length: Int = inputStream.read(buff)
            while (length > 0) {
                outputStream.write(buff, 0, length)
                length = inputStream.read(buff)
            }
            outputStream.flush()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
