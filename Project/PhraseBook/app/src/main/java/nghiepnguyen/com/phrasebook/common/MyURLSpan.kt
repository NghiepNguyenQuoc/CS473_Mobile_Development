package nghiepnguyen.com.phrasebook.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.style.ClickableSpan
import android.view.View
import nghiepnguyen.com.phrasebook.activity.WebViewActivity

class MyURLSpan(private val mContext: Context, private val url: String) : ClickableSpan() {

    override fun onClick(arg0: View) {
        if (url.startsWith("http")) {
            val i = Intent(mContext, WebViewActivity::class.java)
            i.putExtra(Constants.BUNDLE_LINK, url)
            mContext.startActivity(i)
        } else if (url.startsWith("tel")) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse(url)
            mContext.startActivity(intent)
        }
    }
}
