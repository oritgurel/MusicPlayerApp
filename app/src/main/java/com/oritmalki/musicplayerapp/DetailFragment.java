package com.oritmalki.musicplayerapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.oritmalki.musicplayerapp.model.Song;
import static com.oritmalki.musicplayerapp.PagerSwipeFragment.ARGS_SELECTED_SONG_POSITION;

public class DetailFragment extends Fragment {

    Song song;
    ImageView bkgArtistImage;

    public static DetailFragment getInstance(int position) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_SELECTED_SONG_POSITION, position);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Song song = SongListPlayerActivity.songs.get(getArguments().getInt(ARGS_SELECTED_SONG_POSITION));
        this.song = song;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_bkg_image_fragment, container, false);
        bkgArtistImage = view.findViewById(R.id.background_image_detail);
        bkgArtistImage.setImageResource(song.getArtist().getArtistImageUri());
        bkgArtistImage.setScaleType(ScaleType.CENTER_CROP);
        return view;

    }



}
