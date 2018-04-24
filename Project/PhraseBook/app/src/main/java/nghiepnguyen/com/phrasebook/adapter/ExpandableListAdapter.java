package nghiepnguyen.com.phrasebook.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import nghiepnguyen.com.phrasebook.common.IExpandListViewExpanded;
import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.common.Constants;
import nghiepnguyen.com.phrasebook.common.DatabaseHelper;
import nghiepnguyen.com.phrasebook.model.Phrase;


public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private final String TAG = ExpandableListAdapter.class.getSimpleName();
    private Context context;
    private Map<Phrase, List<Phrase>> laptopCollections;
    private List<Phrase> laptops;
    private int lastExpandedGroupPosition;
    private DatabaseHelper databaseHelper;
    private OnLongClickListener onlonglist;
    private String databaseName;
    private int isLock;
    private IExpandListViewExpanded listener;

    public ExpandableListAdapter(Context context, List<Phrase> laptops,
                                 Map<Phrase, List<Phrase>> laptopCollections, OnLongClickListener onlongclick, String databaseName, int isLock, IExpandListViewExpanded listener) {
        this.context = context;
        this.laptopCollections = laptopCollections;
        this.laptops = laptops;
        this.onlonglist = onlongclick;
        this.databaseName = databaseName;
        this.isLock = isLock;
        this.listener = listener;
        databaseHelper = new DatabaseHelper(context, databaseName);
    }

    public Object getChild(int groupPosition, int childPosition) {
        return laptopCollections.get(laptops.get(groupPosition)).get(
                childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final Phrase laptop = (Phrase) getChild(groupPosition, childPosition);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_expandable, null);
        }
        convertView.setBackgroundColor(Color.parseColor("#c9c8c8"));
        TextView item = convertView.findViewById(R.id.translate_textview);
        TextView item2 = convertView.findViewById(R.id.spelling_textview);
        item2.setTextColor(Color.parseColor("#a69348"));
        ImageView playSoundButton = convertView.findViewById(R.id.sound_button);
        playSoundButton.setVisibility(isLock == 1 ? View.VISIBLE : View.GONE);
        playSoundButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
                    String language = null, suffixName = null;
                    if (databaseName.equals(Constants.DATABASE_VN_NAME)) {
                        language = Constants.VIETNAMESE;
                        suffixName = "_m.ogg";
                    } else if (databaseName.equals(Constants.DATABASE_HQ_NAME)) {
                        language = Constants.KOREAN;
                        suffixName = "_f.ogg";
                    } else if (databaseName.equals(Constants.DATABASE_NB_NAME)) {
                        language = Constants.JAPANESE;
                        suffixName = "_f.ogg";
                    } else if (databaseName.equals(Constants.DATABASE_TQ_NAME)) {
                        language = Constants.CHINESE;
                        suffixName = "_m.ogg";
                    }

                    MediaPlayer mp = new MediaPlayer();
                    List<Phrase> child = laptopCollections.get(laptops.get(groupPosition));
                    AssetFileDescriptor descriptor = context.getAssets().openFd(language + child.get(childPosition).getSound() + suffixName);
                    mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                    descriptor.close();
                    mp.setOnCompletionListener(new OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                    mp.prepare();
                    mp.setVolume(1f, 1f);
                    mp.start();
                } catch (Exception e) {
                    Log.e(TAG, "Can not play the sound");
                }
            }
        });

        item.setText(laptop.getMean());
        item2.setText(laptop.getPronunciation());
        String str = "I have learned phrase VietNamese\n" + "Phrase: "
                + laptop.getPhrase() + "\nPronuciation: " + laptop.getPronunciation() + "\nMean: " + laptop.getMean();
        convertView.setTag(str);
        convertView.setOnLongClickListener(onlonglist);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return laptopCollections.get(laptops.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {

        return laptops.get(groupPosition);
    }

    public int getGroupCount() {
        return laptops.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        final Phrase laptopName = (Phrase) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_item, null);
        }
        TextView item = convertView.findViewById(R.id.source_textview);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(laptopName.getPhrase());

        final ImageView favorite = convertView.findViewById(R.id.favorite);
        if (laptopName.getNumber() == 1)
            favorite.setImageResource(R.drawable.ic_fav);
        else
            favorite.setImageResource(R.drawable.ic_fav_press);

        favorite.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (laptopName.getNumber() == 1) {
                    databaseHelper.UpdatePhrase(0, laptopName.getId());
                    laptopName.setNumber(0);
                    favorite.setImageResource(R.drawable.ic_fav_press);
                } else {
                    databaseHelper.UpdatePhrase(1, laptopName.getId());
                    laptopName.setNumber(1);
                    favorite.setImageResource(R.drawable.ic_fav);
                }
            }
        });
        return convertView;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        // TODO Auto-generated method stub
        super.onGroupExpanded(groupPosition);
        if (groupPosition != lastExpandedGroupPosition) {
            listener.onGroupExpanded(lastExpandedGroupPosition);
        }
        lastExpandedGroupPosition = groupPosition;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        // TODO Auto-generated method stub
        super.onGroupCollapsed(groupPosition);
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}