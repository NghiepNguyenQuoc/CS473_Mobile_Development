package nghiepnguyen.com.phrasebook.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.model.Category;

public class CustomListViewAdapter extends ArrayAdapter<Category> {

    Context context;

    public CustomListViewAdapter(Context context, int resourceId,
                                 List<Category> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /* private view holder class */
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Category rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        // holder.txtDesc.setText(rowItem.getDescription());
        holder.txtTitle.setText(rowItem.getNamecategory());
        /*holder.imageView.setImageDrawable(this.context.getResources()
                .getDrawable(
                        this.context.getResources().getIdentifier(
                                "drawable/" + rowItem.getImg(), "drawable",
                                this.context.getPackageName())));*/

        return convertView;
    }
}