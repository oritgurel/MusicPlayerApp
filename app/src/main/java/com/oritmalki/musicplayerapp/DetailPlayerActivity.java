package com.oritmalki.musicplayerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.oritmalki.musicplayerapp.model.Song;

import java.util.ArrayList;
import java.util.List;

import static com.oritmalki.musicplayerapp.PagerSwipeFragment.SONG_POSITION;

/**
 * Created by Orit on 10.4.2018.
 */

public class DetailPlayerActivity extends AppCompatActivity {

    Song song;
    TextView songName;
    TextView artistName;
    ImageView bkgImg;
    ViewPager detailPager;
    Button nextSong;
    Button prevSong;
    ViewPager.OnClickListener onClickListener;
    List<Song> songs = new ArrayList<>(SongListPlayerActivity.songs);
    List<DetailFragment> detailFragments = new ArrayList<>();
    int pagerPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_player_activity);
        song = SongListPlayerActivity.songs.get(getIntent().getExtras().getInt(SONG_POSITION));
        songName = findViewById(R.id.detail_song_name);
        artistName = findViewById(R.id.detail_artist_name);
        bkgImg = findViewById(R.id.background_image_detail);
        nextSong = findViewById(R.id.next_song_detail_butt);
        prevSong = findViewById(R.id.prev_song_detail_butt);
        songName.setText(song.getSongTitle());
        artistName.setText(song.getArtist().getArtistName());
        setupImageBkgPager();
        detailPager.setCurrentItem(getIntent().getExtras().getInt(SONG_POSITION));
//        bkgImg.setImageResource(song.getArtist().getArtistImageUri());

        onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.next_song_detail_butt:
                        if (detailPager.getCurrentItem() != detailFragments.size() - 1)
                            detailPager.setCurrentItem(detailPager.getCurrentItem() + 1);
                        break;
                    case R.id.prev_song_detail_butt:
                        if (detailPager.getCurrentItem() != 0)
                            detailPager.setCurrentItem(detailPager.getCurrentItem() - 1);
                        break;
                }
            }
        };

        nextSong.setOnClickListener(onClickListener);
        prevSong.setOnClickListener(onClickListener);


    }

    public void setupImageBkgPager() {
        detailPager = findViewById(R.id.detail_view_pager);
        for (Song song : songs) {
            DetailFragment detailFragment = DetailFragment.getInstance(songs.indexOf(song));
            detailFragments.add(detailFragment);
        }
        DetailViewPagerAdapter adapter = new DetailViewPagerAdapter(getSupportFragmentManager(), detailFragments);
        detailPager.setAdapter(adapter);
        detailPager.addOnPageChangeListener(new OnPageChangeListener() {

            //this is for selecting item in recyclerview when swiped on viewPagerBar
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {

                pagerPosition = position;
                songName.setText(songs.get(position).getSongTitle());
                artistName.setText(songs.get(position).getArtist().getArtistName());
                bkgImg.setImageResource(song.getArtist().getArtistImageUri());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, SongListPlayerActivity.class);
        intent.putExtra(SONG_POSITION, pagerPosition);
        startActivity(intent);
    }
}
