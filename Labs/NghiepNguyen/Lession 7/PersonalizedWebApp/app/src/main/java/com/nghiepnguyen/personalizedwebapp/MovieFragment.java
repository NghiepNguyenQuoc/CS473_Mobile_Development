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
public class MovieFragment extends Fragment {


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        ListView listView = view.findViewById(R.id.favorite_web_listview);
        List<WebModel> webModels = new ArrayList<>();
        webModels.add(new WebModel(R.drawable.vimeo_icon, "Vimeo.com - Official Site | Professional Video Platform\u200E", "https://vimeo.com/"));
        webModels.add(new WebModel(R.drawable.youtube_icon, "YouTube", "https://www.youtube.com/"));
        webModels.add(new WebModel(R.drawable.imdb_icon, "IMDb - Movies, TV and Celebrities - IMDb", "http://www.imdb.com/"));
        WebAdapter adapter = new WebAdapter(getActivity(), webModels);
        listView.setAdapter(adapter);
        return view;
    }

}
