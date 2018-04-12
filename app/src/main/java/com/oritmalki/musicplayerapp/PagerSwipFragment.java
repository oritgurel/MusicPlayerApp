package com.oritmalki.musicplayerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oritmalki.musicplayerapp.model.Song;



public class PagerSwipFragment extends Fragment implements View.OnClickListener {

    Song song;
    TextView songNameBar;
    TextView artistNameBar;
    ViewGroup songBarTextWrapper;

    public static final String SONG_INFO = "SONG_INFO";

    final static String ARGS_SELECTED_SONG = "args_selected_song";

    public static PagerSwipFragment getInstance(Song song) {
        PagerSwipFragment pagerSwapFragment = new PagerSwipFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGS_SELECTED_SONG, song);
        pagerSwapFragment.setArguments(bundle);
        return pagerSwapFragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Song song = (Song) getArguments().getSerializable(ARGS_SELECTED_SONG);
        this.song = song;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_bar_fragment_item, container, false);

        songNameBar = view.findViewById(R.id.song_name_bar);
        artistNameBar = view.findViewById(R.id.artist_name_bar);
        songBarTextWrapper = view.findViewById(R.id.song_ber_text_wrapper);
        songBarTextWrapper.setOnClickListener(this);

        songNameBar.setText(song.getSongTitle());
        artistNameBar.setText(song.getArtistName());

        return view;

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), DetailPlayerActivity.class);
        intent.putExtra(SONG_INFO, song);
        startActivity(intent);

    }
}
