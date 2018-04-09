package nghiepnguyen.com.phrasebook.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.activity.MainActivity;
import nghiepnguyen.com.phrasebook.activity.PhraseActivity;
import nghiepnguyen.com.phrasebook.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;
    private List<Category> categoryList;

    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView imageView;
        TextView txtTitle;

        ViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.category_textview);
            imageView = view.findViewById(R.id.icon_imageview);
        }
    }

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Category rowItem = categoryList.get(position);
        holder.txtTitle.setText(rowItem.getNamecategory());
        holder.imageView.setImageDrawable(this.context.getResources().getDrawable(
                context.getResources().getIdentifier("drawable/" + rowItem.getImg(), "drawable", context.getPackageName())));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PhraseActivity.class);
                intent.putExtra(MainActivity.VALUE_CATEGORY, categoryList.get(position).getId()); // Your
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}