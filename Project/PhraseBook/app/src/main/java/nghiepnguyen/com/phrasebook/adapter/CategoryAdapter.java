package nghiepnguyen.com.phrasebook.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.activity.MainActivity;
import nghiepnguyen.com.phrasebook.activity.PhraseActivity;
import nghiepnguyen.com.phrasebook.model.Category;

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Category rowItem = categoryList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category, null);
            holder = new ViewHolder();
            holder.txtTitle = convertView.findViewById(R.id.category_textview);
            holder.imageView = convertView.findViewById(R.id.icon_imageview);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(rowItem.getNamecategory());
        holder.imageView.setImageDrawable(this.context.getResources().getDrawable(
                context.getResources().getIdentifier("drawable/" + rowItem.getImg(), "drawable", context.getPackageName())));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PhraseActivity.class);
                intent.putExtra(MainActivity.VALUE_CATEGORY, categoryList.get(position).getId()); // Your
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}