package nghiepnguyen.com.phrasebook.adapter

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.URLSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import nghiepnguyen.com.phrasebook.R
import nghiepnguyen.com.phrasebook.activity.MainActivity
import nghiepnguyen.com.phrasebook.activity.PhraseActivity
import nghiepnguyen.com.phrasebook.activity.PhraseDetailFragment
import nghiepnguyen.com.phrasebook.common.Constants
import nghiepnguyen.com.phrasebook.common.MyURLSpan
import nghiepnguyen.com.phrasebook.model.Category

private class CategoryAdapter(private val mContext: MainActivity, private val categoryList: List<Category>,
                              private val databaseName: String, private val language: String,
                              private val mTwoPane: Boolean) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    internal inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // each data item is just a string in this case
        var imageView: ImageView = view.findViewById(R.id.icon_imageview)
        var txtTitle: TextView = view.findViewById(R.id.category_textview)
        var txtDesciption: TextView = view.findViewById(R.id.description_textview)

    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CategoryAdapter.ViewHolder {
        // create a new view
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rowItem = categoryList[position]
        holder.txtTitle.text = rowItem.namecategory
        holder.imageView.setImageDrawable(
                mContext.resources.getDrawable(mContext.resources.getIdentifier("drawable/" + rowItem.img!!, "drawable", mContext.packageName)))
        if (TextUtils.isEmpty(rowItem.description)) {
            holder.txtDesciption.visibility = View.GONE
        } else {
            holder.txtDesciption.visibility = View.VISIBLE
            holder.txtDesciption.text = Html.fromHtml(rowItem.description)
            val text = holder.txtDesciption.text
            if (text is Spannable) {
                val end = text.length
                val urls = text.getSpans(0, end, URLSpan::class.java)
                val style = SpannableStringBuilder(text)
                style.clearSpans()
                for (urlSpan in urls) {
                    val myURLSpan = MyURLSpan(mContext, urlSpan.url)
                    style.setSpan(myURLSpan, text.getSpanStart(urlSpan),
                            text.getSpanEnd(urlSpan),
                            Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

                }
                holder.txtDesciption.text = style
            }
        }

        holder.itemView.setOnClickListener {
            if (mTwoPane) {
                val fragment = PhraseDetailFragment()
                val bundle = Bundle()
                bundle.putString(Constants.BUNDLE_DATABASE, databaseName)
                bundle.putString(Constants.BUNDLE_LANGUAGE, language)
                bundle.putInt(Constants.BUNDLE_LOCK, rowItem.lock)
                bundle.putInt(Constants.BUNDLE_CATEGORY, categoryList[position].id) // Your
                fragment.arguments = bundle
                mContext.supportFragmentManager.beginTransaction()
                        .replace(R.id.item_detail_container, fragment, "fragment")
                        .commit()
            } else {
                val intent = Intent(mContext, PhraseActivity::class.java)
                intent.putExtra(Constants.BUNDLE_DATABASE, databaseName)
                intent.putExtra(Constants.BUNDLE_LANGUAGE, language)
                intent.putExtra(Constants.BUNDLE_LOCK, rowItem.lock)
                intent.putExtra(Constants.BUNDLE_CATEGORY_TEXT, categoryList[position].namecategory)
                intent.putExtra(Constants.BUNDLE_CATEGORY, categoryList[position].id) // Your
                mContext.startActivity(intent)
            }
        }
        when (position) {
            0 -> holder.itemView.setPadding(Constants.convertPixelsToDp(10f, mContext), Constants.convertPixelsToDp(5f, mContext),
                    Constants.convertPixelsToDp(10f, mContext), Constants.convertPixelsToDp(0f, mContext))
            itemCount - 1 -> holder.itemView.setPadding(Constants.convertPixelsToDp(10f, mContext), Constants.convertPixelsToDp(5f, mContext),
                    Constants.convertPixelsToDp(10f, mContext), Constants.convertPixelsToDp(5f, mContext))
            else -> holder.itemView.setPadding(Constants.convertPixelsToDp(10f, mContext), Constants.convertPixelsToDp(5f, mContext),
                    Constants.convertPixelsToDp(10f, mContext), Constants.convertPixelsToDp(0f, mContext))
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}