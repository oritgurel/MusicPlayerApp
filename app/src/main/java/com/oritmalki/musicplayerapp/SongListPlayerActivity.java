package com.oritmalki.musicplayerapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.oritmalki.musicplayerapp.model.DemoSongList;
import com.oritmalki.musicplayerapp.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SongListPlayerActivity extends AppCompatActivity implements SongListAdapterCallback {

    //TODO add favorite list
    //TODO ChoosePlaylist/Album activity
    //TODO animations: button pressed, image parallax fade
    //TODO fix seekBar View (so it won't crash)
    //TODO update bottom nav
    //TODO add demo artist image resources for each song object

    RecyclerView recyclerView;
    View selectedSongName;
    View previouslySelectedSongName;
    int selectedSongPosition;
    int lastCheckedPosition = -1;
    Button playPauseMainButt;
    Button playPauseBarButt;
    ImageView pagerSmallImage;
    ViewPager viewPagerBar;
    SongListAdapter adapter;
    boolean isPlaying = false;
    public static View.OnClickListener playListener;
    List<Song> songs = new ArrayList<>();
    List<PagerSwipeFragment> swapFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list_layout);

        DemoSongList.fillDemoData(songs);

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SongListAdapter(songs, this);
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
        pagerSmallImage = findViewById(R.id.pager_image);

        setupViewPagerBar();


    }

    //get selected item info from adapter
    @Override
    public void onSongListItemSelected(View song, int position) {
        this.selectedSongName = song;
        this.selectedSongPosition = position;
        viewPagerBar.setCurrentItem(position);
        pagerSmallImage.setImageResource(songs.get(position).getArtist().getArtistImageUri());



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

    public void setupViewPagerBar() {
        viewPagerBar = findViewById(R.id.viewpager);
        for (Song song : songs) {
            PagerSwipeFragment swapFragment = PagerSwipeFragment.getInstance(song);
            swapFragments.add(swapFragment);
        }
        ViewPagerAdapter swapAdapter = new ViewPagerAdapter(getSupportFragmentManager(), swapFragments);
        viewPagerBar.setAdapter(swapAdapter);

        viewPagerBar.addOnPageChangeListener(new OnPageChangeListener() {

            //this is for selecting item in recyclerview when swiped on viewPagerBar
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override public boolean onPreDraw() {
                        recyclerView.findViewHolderForAdapterPosition(position).itemView.performClick();
                        recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                        return true; }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
