package nghiepnguyen.com.phrasebook.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.common.Constants;

public class WebViewActivity extends AppCompatActivity {


    private ProgressBar loadingWebview;
    private String strTitle;
    private String strUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        // get data
        Bundle data = getIntent().getExtras();
        if (data != null) {
            strUrl = data.getString(Constants.BUNDLE_LINK, "");
        }
        WebView webView = findViewById(R.id.webView);
        loadingWebview = findViewById(R.id.loading_webview);
        loadingWebview.setVisibility(View.VISIBLE);
        webView.loadUrl(strUrl);
        setTitle(strUrl);
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript Support
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                Log.e("LINK", url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress >= 100) {
                    loadingWebview.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
