package nghiepnguyen.com.phrasebook.adapter

import android.app.Activity
import android.content.Context
import android.content.res.AssetFileDescriptor
import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView

import nghiepnguyen.com.phrasebook.R
import nghiepnguyen.com.phrasebook.common.Constants
import nghiepnguyen.com.phrasebook.common.DatabaseHelper
import nghiepnguyen.com.phrasebook.common.IExpandListViewExpanded
import nghiepnguyen.com.phrasebook.common.ILongClickItem
import nghiepnguyen.com.phrasebook.model.Phrase


class ExpandableListAdapter(private val context: Context, private val laptops: List<Phrase>,
                            private val laptopCollections: Map<Phrase, List<Phrase>>, private val databaseName: String, private val isLock: Int, private val expandListener: IExpandListViewExpanded,
                            private val longClickListener: ILongClickItem?) : BaseExpandableListAdapter() {
    private val TAG = ExpandableListAdapter::class.java.simpleName
    private var lastExpandedGroupPosition: Int = 0
    private val databaseHelper: DatabaseHelper

    init {
        databaseHelper = DatabaseHelper(context, databaseName)
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return laptopCollections[laptops[groupPosition]].get(
                childPosition)
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int,
                              isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val phrase = getChild(groupPosition, childPosition) as Phrase
        val inflater = (context as Activity).layoutInflater

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_expandable, null)
        }
        convertView!!.setBackgroundColor(Color.parseColor("#c9c8c8"))
        val item = convertView.findViewById<TextView>(R.id.translate_textview)
        val item2 = convertView.findViewById<TextView>(R.id.spelling_textview)
        item2.setTextColor(Color.parseColor("#a69348"))
        val playSoundButton = convertView.findViewById<ImageView>(R.id.sound_button)
        playSoundButton.visibility = if (isLock == 1) View.VISIBLE else View.GONE
        playSoundButton.setOnClickListener {
            try {
                var language: String? = null
                var suffixName: String? = null
                if (databaseName == Constants.DATABASE_VN_NAME) {
                    language = Constants.VIETNAMESE
                    suffixName = "_m.ogg"
                } else if (databaseName == Constants.DATABASE_HQ_NAME) {
                    language = Constants.KOREAN
                    suffixName = "_f.ogg"
                } else if (databaseName == Constants.DATABASE_NB_NAME) {
                    language = Constants.JAPANESE
                    suffixName = "_f.ogg"
                } else if (databaseName == Constants.DATABASE_TQ_NAME) {
                    language = Constants.CHINESE
                    suffixName = "_m.ogg"
                }

                val mp = MediaPlayer()
                val child = laptopCollections[laptops[groupPosition]]
                val descriptor = context.getAssets().openFd(language + child.get(childPosition).sound + suffixName)
                mp.setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
                descriptor.close()
                mp.setOnCompletionListener { mp -> mp.release() }
                mp.prepare()
                mp.setVolume(1f, 1f)
                mp.start()
            } catch (e: Exception) {
                Log.e(TAG, "Can not play the sound")
            }
        }

        item.text = phrase.mean
        item2.text = phrase.pronunciation
        convertView.setOnLongClickListener {
            longClickListener?.onLongClickItem(phrase)
            false
        }
        convertView.tag = phrase.id
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return laptopCollections[laptops[groupPosition]].size
    }

    override fun getGroup(groupPosition: Int): Any {

        return laptops[groupPosition]
    }

    override fun getGroupCount(): Int {
        return laptops.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean,
                              convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val laptopName = getGroup(groupPosition) as Phrase
        if (convertView == null) {
            val infalInflater = context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.group_item, null)
        }
        val item = convertView!!.findViewById<TextView>(R.id.source_textview)
        item.setTypeface(null, Typeface.BOLD)
        item.text = laptopName.phrase

        val favorite = convertView.findViewById<ImageView>(R.id.favorite)
        if (laptopName.number == 1)
            favorite.setImageResource(R.drawable.ic_fav)
        else
            favorite.setImageResource(R.drawable.ic_fav_press)

        favorite.setOnClickListener {
            if (laptopName.number == 1) {
                databaseHelper.updatePhrase(0, laptopName.id)
                laptopName.number = 0
                favorite.setImageResource(R.drawable.ic_fav_press)
            } else {
                databaseHelper.updatePhrase(1, laptopName.id)
                laptopName.number = 1
                favorite.setImageResource(R.drawable.ic_fav)
            }
        }
        return convertView
    }

    override fun onGroupExpanded(groupPosition: Int) {
        // TODO Auto-generated method stub
        super.onGroupExpanded(groupPosition)
        if (groupPosition != lastExpandedGroupPosition) {
            expandListener.onGroupExpanded(lastExpandedGroupPosition)
        }
        lastExpandedGroupPosition = groupPosition
    }

    override fun onGroupCollapsed(groupPosition: Int) {
        // TODO Auto-generated method stub
        super.onGroupCollapsed(groupPosition)
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}