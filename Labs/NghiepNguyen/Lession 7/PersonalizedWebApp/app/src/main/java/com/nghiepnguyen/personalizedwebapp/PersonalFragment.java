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
    private ListView listView;

    public PersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        listView = view.findViewById(R.id.favorite_web_listview);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        List<WebModel> webModels = new ArrayList<>();
        webModels.add(new WebModel(R.drawable.spotify_icon, "Spotify: Music for everyone", "www.spotify.com"));
        webModels.add(new WebModel(R.drawable.social_soundcloud_icon, "SoundCloud – Listen to free music and podcasts on SoundCloud", "https://soundcloud.com/"));
        webModels.add(new WebModel(R.drawable.itunes_256, "iTunes - Music - Apple", "https://www.apple.com/itunes/music/"));
        WebAdapter adapter = new WebAdapter(getContext(), webModels);
        listView.setAdapter(adapter);
    }
}
