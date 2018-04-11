package com.oritmalki.musicplayerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.oritmalki.musicplayerapp.model.DemoSongList;
import com.oritmalki.musicplayerapp.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SongListPlayerActivity extends AppCompatActivity implements SongListAdapterCallback {

    RecyclerView recyclerView;
    View selectedSongName;
    View previouslySelectedSongName;
    int selectedSongPosition;
    int lastCheckedPosition = -1;
    Button playPauseMainButt;
    Button playPauseBarButt;
    boolean isPlaying = false;
    public static View.OnClickListener playListener;
    List<Song> songs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list_layout);

        DemoSongList.fillDemoData(songs);

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final SongListAdapter adapter = new SongListAdapter(songs, this);
        recyclerView.setAdapter(adapter);

        playPauseBarButt = findViewById(R.id.pager_play);
        playPauseMainButt = findViewById(R.id.play_pause_butt);
        playListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                //play song... and update view:

                if (selectedSongName != null) {



                    if (!isPlaying) {

                        setViewPlaying(selectedSongName);

                    }
                    else {
                        setViewPause(selectedSongName);
                    }
                }

            }
        };

        playPauseMainButt.setOnClickListener(playListener);
        playPauseBarButt.setOnClickListener(playListener);

    }

    //get selected item info from adapter
    @Override
    public void onSongListItemSelected(View song, int position) {
        this.selectedSongName = song;
        this.selectedSongPosition = position;

        //making it possible to play one song at a time
        if (previouslySelectedSongName != null) {

            if (position != lastCheckedPosition) {
                setViewPause(previouslySelectedSongName);
            }
        }
    }

    public void setViewPlaying(View view) {
        isPlaying = true;
        playPauseMainButt.setText(R.string.pause);
        ((TextView)view).setTextColor(getResources().getColor(R.color.colorAccent));
        playPauseBarButt.setBackground(getResources().getDrawable(R.drawable.ic_pause));
        lastCheckedPosition = selectedSongPosition;
        //take reference to (view of) last song  played
        previouslySelectedSongName = selectedSongName;
    }

    public void setViewPause(View view) {
        isPlaying = false;
        playPauseMainButt.setText(R.string.play);
        ((TextView)view).setTextColor(getResources().getColor(R.color.white));
        playPauseBarButt.setBackground(getResources().getDrawable(R.drawable.ic_play_track_detail_16));

    }
}
