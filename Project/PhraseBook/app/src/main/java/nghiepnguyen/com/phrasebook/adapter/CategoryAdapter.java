package nghiepnguyen.com.phrasebook.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import nghiepnguyen.com.phrasebook.activity.PhraseDetailFragment;
import nghiepnguyen.com.phrasebook.model.Category;
import nghiepnguyen.com.phrasebook.common.Constants;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private MainActivity mContext;
    private List<Category> categoryList;
    private String databaseName;
    private boolean mTwoPane;

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

    public CategoryAdapter(MainActivity mParentActivity, List<Category> categoryList, String databaseName, boolean mTwoPane) {
        this.mContext = mParentActivity;
        this.categoryList = categoryList;
        this.databaseName = databaseName;
        this.mTwoPane = mTwoPane;
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
        final Category rowItem = categoryList.get(position);
        holder.txtTitle.setText(rowItem.getNamecategory());
        holder.imageView.setImageDrawable(mContext.getResources().getDrawable(
                mContext.getResources().getIdentifier("drawable/" + rowItem.getImg(), "drawable", mContext.getPackageName())));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTwoPane) {
                    Fragment fragment = new PhraseDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.BUNDLE_DATABASE, databaseName);
                    bundle.putInt(Constants.BUNDLE_LOCK, rowItem.getLock());
                    bundle.putInt(MainActivity.VALUE_CATEGORY, categoryList.get(position).getId()); // Your
                    fragment.setArguments(bundle);
                    mContext.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment, "fragment")
                            .commit();
                } else {
                    Intent intent = new Intent(mContext, PhraseActivity.class);
                    intent.putExtra(Constants.BUNDLE_DATABASE, databaseName);
                    intent.putExtra(Constants.BUNDLE_LOCK, rowItem.getLock());
                    intent.putExtra(MainActivity.VALUE_CATEGORY, categoryList.get(position).getId()); // Your
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}