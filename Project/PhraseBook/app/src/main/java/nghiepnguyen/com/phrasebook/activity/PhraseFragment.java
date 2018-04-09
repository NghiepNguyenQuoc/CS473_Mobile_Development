package nghiepnguyen.com.phrasebook.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import nghiepnguyen.com.phrasebook.R;
import nghiepnguyen.com.phrasebook.adapter.CategoryAdapter;
import nghiepnguyen.com.phrasebook.model.Category;
import nghiepnguyen.com.phrasebook.model.Constants;
import nghiepnguyen.com.phrasebook.model.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhraseFragment extends Fragment {
    private Context mContext;
    private String databaseName;

    public PhraseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        databaseName = getArguments().getString(Constants.BUNDLE_DATABASE);
        return inflater.inflate(R.layout.fragment_phrase, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatabaseHelper databaseHelper = new DatabaseHelper(mContext, databaseName);
        File database = mContext.getDatabasePath(databaseName);
        if (!database.exists()) {
            databaseHelper.getReadableDatabase();
            copyDatabase();
        }

        List<Category> rowItems = databaseHelper.GetAllCategory();
        RecyclerView mRecyclerView = getView().findViewById(R.id.category_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        CategoryAdapter adapter = new CategoryAdapter(mContext, rowItems, databaseName);
        mRecyclerView.setAdapter(adapter);
    }


    public void copyDatabase() {
        try {
            InputStream inputStream = mContext.getAssets().open(databaseName);
            String outFileName = Constants.DBLOCATION + databaseName;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int lenght;
            while ((lenght = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, lenght);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
