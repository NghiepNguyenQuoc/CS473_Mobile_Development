package nghiepnguyen.com.phrasebook.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
        if (url.startsWith("http")) {
            Intent i = new Intent(mContext, WebViewActivity.class);
            i.putExtra(Constants.BUNDLE_LINK, url);
            mContext.startActivity(i);
        } else if (url.startsWith("tel")) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(url));
            mContext.startActivity(intent);
        }
    }
}
