package com.nghiepnguyen.personalizedwebapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment {

    public PersonalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        ListView listView = view.findViewById(R.id.favorite_web_listview);
        List<WebModel> webModels = new ArrayList<>();
        webModels.add(new WebModel(R.drawable.cbs_news, "CBS News - Breaking News, Live News stream 24x7", "https://www.cbsnews.com/"));
        webModels.add(new WebModel(R.drawable.cnn_icon, "CNN - Breaking News, Latest News and Videos", "https://www.cnn.com/"));
        webModels.add(new WebModel(R.drawable.newyorktime_icon, "The New York Times - Breaking News, World News & Multimedia", "https://www.nytimes.com/"));
        webModels.add(new WebModel(R.drawable.amazon_icon, "Amazon.com: Online Shopping for Electronics, Apparel, Computers", "https://www.amazon.com/"));
        WebAdapter adapter = new WebAdapter(getActivity(), webModels);
        listView.setAdapter(adapter);
        return view;
    }
}
