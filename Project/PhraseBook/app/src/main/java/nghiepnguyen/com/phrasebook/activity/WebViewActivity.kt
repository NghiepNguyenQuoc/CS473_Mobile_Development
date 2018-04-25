package nghiepnguyen.com.phrasebook.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import nghiepnguyen.com.phrasebook.R
import nghiepnguyen.com.phrasebook.common.Constants

class WebViewActivity : AppCompatActivity() {


    private var loadingWebview: ProgressBar? = null
    private val strTitle: String? = null
    private var strUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
    }

    override fun onStart() {
        super.onStart()
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(false)
        actionBar.setDisplayShowTitleEnabled(true)
        actionBar.setDisplayUseLogoEnabled(false)
        // get data
        val data = intent.extras
        if (data != null) {
            strUrl = data.getString(Constants.BUNDLE_LINK, "")
        }
        val webView = findViewById<WebView>(R.id.webView)
        loadingWebview = findViewById(R.id.loading_webview)
        loadingWebview!!.visibility = View.VISIBLE
        webView.loadUrl(strUrl)
        title = strUrl
        webView.settings.javaScriptEnabled = true // Enable JavaScript Support
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                Log.e("LINK", url)
                return true
            }
        }
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress >= 100) {
                    loadingWebview!!.visibility = View.GONE
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}
