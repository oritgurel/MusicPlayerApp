package com.oritmalki.musicplayerapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.oritmalki.musicplayerapp.model.Song;

/**
 * Created by Orit on 10.4.2018.
 */

public class DetailPlayerActivity extends AppCompatActivity {

    Song song;
    TextView songName;
    TextView artistName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_player_activity);

        song = (Song) getIntent().getExtras().getSerializable(PagerSwipFragment.SONG_INFO);
        songName = findViewById(R.id.detail_song_name);
        artistName = findViewById(R.id.detail_artist_name);

        songName.setText(song.getSongTitle());
        artistName.setText(song.getArtistName());
    }
}
