package nghiepnguyen.com.phrasebook.activity

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import nghiepnguyen.com.phrasebook.R
import nghiepnguyen.com.phrasebook.common.Constants

open class PhraseActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phrase)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(false)
        actionBar.setDisplayShowTitleEnabled(true)
        actionBar.setDisplayUseLogoEnabled(false)

        val extras = intent.extras
        val catagoryName = extras!!.getString(Constants.BUNDLE_CATEGORY_TEXT)
        actionBar.title = catagoryName

        val fragment = PhraseDetailFragment()
        fragment.arguments = extras
        supportFragmentManager.beginTransaction()
                .replace(R.id.content_phrase, fragment, "fragment")
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.phrase_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when (item.itemId) {
            R.id.search -> {
            }
            R.id.voice -> startVoiceRecognitionActivity()
            R.id.favorite -> onOpenFavorite()
            android.R.id.home -> {
                val myFragment = supportFragmentManager.findFragmentByTag("fragment") as PhraseDetailFragment
                if (myFragment.isVisible) {
                    if (myFragment.isEmpty) {
                        myFragment.createCollection()
                        myFragment.fillDataToListView()
                        myFragment.isEmpty = false
                    } else {
                        finish()
                    }
                }
            }
        }
        return true
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

    override fun onQueryTextSubmit(query: String): Boolean {
        // Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        return false
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
}
