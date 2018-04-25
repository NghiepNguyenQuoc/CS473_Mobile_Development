package nghiepnguyen.com.phrasebook.common;

import android.content.Context;
import android.content.Intent;
import android.text.style.ClickableSpan;
import android.view.View;

import nghiepnguyen.com.phrasebook.activity.WebViewActivity;

public class MyURLSpan extends ClickableSpan {
    private String url;
    private Context mContext;

    public MyURLSpan(Context mContext, String url) {
        this.url = url;
        this.mContext = mContext;
    }

    @Override
    public void onClick(View arg0) {

        Intent i = new Intent(mContext, WebViewActivity.class);
        i.putExtra(Constants.BUNDLE_LINK, url);
        mContext.startActivity(i);
    }
}
