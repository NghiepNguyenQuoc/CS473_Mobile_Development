package nghiepnguyen.com.phrasebook.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.URLSpan;
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
import nghiepnguyen.com.phrasebook.common.Constants;
import nghiepnguyen.com.phrasebook.common.MyURLSpan;
import nghiepnguyen.com.phrasebook.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private MainActivity mContext;
    private List<Category> categoryList;
    private String databaseName;
    private String language;
    private boolean mTwoPane;

    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesciption;

        ViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.category_textview);
            txtDesciption = view.findViewById(R.id.description_textview);
            imageView = view.findViewById(R.id.icon_imageview);
        }
    }

    public CategoryAdapter(MainActivity mParentActivity, List<Category> categoryList, String databaseName, String language, boolean mTwoPane) {
        this.mContext = mParentActivity;
        this.categoryList = categoryList;
        this.databaseName = databaseName;
        this.language = language;
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
        if (TextUtils.isEmpty(rowItem.getDescription())) {
            holder.txtDesciption.setVisibility(View.GONE);
        } else {
            holder.txtDesciption.setVisibility(View.VISIBLE);
            holder.txtDesciption.setText(Html.fromHtml(rowItem.getDescription()));
            CharSequence text = holder.txtDesciption.getText();
            if (text instanceof Spannable) {
                int end = text.length();
                Spannable sp = (Spannable) text;
                URLSpan urls[] = sp.getSpans(0, end, URLSpan.class);
                SpannableStringBuilder style = new SpannableStringBuilder(text);
                style.clearSpans();
                for (URLSpan urlSpan : urls) {
                    MyURLSpan myURLSpan = new MyURLSpan(mContext, urlSpan.getURL());
                    style.setSpan(myURLSpan, sp.getSpanStart(urlSpan),
                            sp.getSpanEnd(urlSpan),
                            Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

                }
                holder.txtDesciption.setText(style);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTwoPane) {
                    Fragment fragment = new PhraseDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.BUNDLE_DATABASE, databaseName);
                    bundle.putString(Constants.BUNDLE_LANGUAGE, language);
                    bundle.putInt(Constants.BUNDLE_LOCK, rowItem.getLock());
                    bundle.putInt(Constants.BUNDLE_CATEGORY, categoryList.get(position).getId()); // Your
                    fragment.setArguments(bundle);
                    mContext.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment, "fragment")
                            .commit();
                } else {
                    Intent intent = new Intent(mContext, PhraseActivity.class);
                    intent.putExtra(Constants.BUNDLE_DATABASE, databaseName);
                    intent.putExtra(Constants.BUNDLE_LANGUAGE, language);
                    intent.putExtra(Constants.BUNDLE_LOCK, rowItem.getLock());
                    intent.putExtra(Constants.BUNDLE_CATEGORY_TEXT, categoryList.get(position).getNamecategory());
                    intent.putExtra(Constants.BUNDLE_CATEGORY, categoryList.get(position).getId()); // Your
                    mContext.startActivity(intent);
                }
            }
        });
        if (position == 0) {
            holder.itemView.setPadding(Constants.convertPixelsToDp(10, mContext), Constants.convertPixelsToDp(5, mContext),
                    Constants.convertPixelsToDp(10, mContext), Constants.convertPixelsToDp(0, mContext));
        } else if (position == getItemCount() - 1) {
            holder.itemView.setPadding(Constants.convertPixelsToDp(10, mContext), Constants.convertPixelsToDp(5, mContext),
                    Constants.convertPixelsToDp(10, mContext), Constants.convertPixelsToDp(5, mContext));
        } else {
            holder.itemView.setPadding(Constants.convertPixelsToDp(10, mContext), Constants.convertPixelsToDp(5, mContext),
                    Constants.convertPixelsToDp(10, mContext), Constants.convertPixelsToDp(0, mContext));
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}