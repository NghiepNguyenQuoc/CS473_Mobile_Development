package nghiepnguyen.com.phrasebook.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.model.DatabaseHelper;
import nghiepnguyen.com.phrasebook.model.Phrase;
import nghiepnguyen.com.phrasebook.model.PhraseDAO;


public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<Phrase, List<Phrase>> laptopCollections;
    private List<Phrase> laptops;
    public int lastExpandedGroupPosition;
    PhraseDAO phraseDAO = null;
    private DatabaseHelper helper;
    private OnLongClickListener onlonglist;

    public ExpandableListAdapter(Activity context, List<Phrase> laptops,
                                 Map<Phrase, List<Phrase>> laptopCollections, OnLongClickListener onlongclick) {
        this.context = context;
        this.laptopCollections = laptopCollections;
        this.laptops = laptops;
        this.onlonglist = onlongclick;

        helper = new DatabaseHelper(this.context, "data", null, 1);
        this.phraseDAO = new PhraseDAO(null, null, null, 1);
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
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_item, null);
        }
        convertView.setBackgroundColor(Color.parseColor("#c9c8c8"));
        TextView item = (TextView) convertView.findViewById(R.id.laptop);
        TextView item2 = (TextView) convertView.findViewById(R.id.laptop2);
        item2.setTextColor(Color.parseColor("#a69348"));
        ImageView delete = (ImageView) convertView.findViewById(R.id.delete);
        delete.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
                    MediaPlayer mp = new MediaPlayer();
                    List<Phrase> child = laptopCollections.get(laptops
                            .get(groupPosition));
                    Uri uri = Uri
                            .parse("android.resource://com.example.phrasebook/raw/"
                                    + child.get(childPosition).getSound()
                                    + "_m");
                    mp = MediaPlayer.create(context, uri);
                    mp.setOnCompletionListener(new OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                    mp.start();
                } catch (Exception e) {
                    // TODO: handle exception
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
        TextView item = (TextView) convertView.findViewById(R.id.laptop);

        item.setTypeface(null, Typeface.BOLD);
        item.setText(laptopName.getPhrase());

        final ImageView favorite = (ImageView) convertView
                .findViewById(R.id.favorite);
        if (laptopName.getNumber() == 1)
            favorite.setImageResource(R.mipmap.ic_fav);
        else
            favorite.setImageResource(R.mipmap.ic_fav_press);

        favorite.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                helper.open();
                if (laptopName.getNumber() == 1) {
                    phraseDAO.UpdatePhrase(0, laptopName.getId());
                    laptopName.setNumber(0);
                    favorite.setImageResource(R.mipmap.ic_fav_press);
                } else {
                    phraseDAO.UpdatePhrase(1, laptopName.getId());
                    laptopName.setNumber(1);
                    favorite.setImageResource(R.mipmap.ic_fav);
                }
                helper.close();
            }
        });
        return convertView;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        // TODO Auto-generated method stub
        super.onGroupExpanded(groupPosition);
        ExpandableListView elw = (ExpandableListView) context
                .findViewById(R.id.laptop_list);
        if (groupPosition != lastExpandedGroupPosition) {
            elw.collapseGroup(lastExpandedGroupPosition);
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