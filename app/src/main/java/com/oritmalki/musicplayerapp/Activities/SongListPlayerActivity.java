package com.oritmalki.musicplayerapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.oritmalki.musicplayerapp.Adapters.SongListAdapter;
import com.oritmalki.musicplayerapp.Adapters.ViewPagerAdapter;
import com.oritmalki.musicplayerapp.Fragments.FavoritesListFragment;
import com.oritmalki.musicplayerapp.Fragments.LibraryArtistsFragment;
import com.oritmalki.musicplayerapp.Fragments.PagerSwipeFragment;
import com.oritmalki.musicplayerapp.Interfaces.SongListAdapterCallback;
import com.oritmalki.musicplayerapp.R;
import com.oritmalki.musicplayerapp.model.DemoSongList;
import com.oritmalki.musicplayerapp.model.Song;

import java.util.ArrayList;
import java.util.List;

import static com.oritmalki.musicplayerapp.Fragments.PagerSwipeFragment.SONG_POSITION;

public class SongListPlayerActivity extends AppCompatActivity implements SongListAdapterCallback, LibraryArtistsFragment.OnFragmentInteractionListener, FavoritesListFragment.OnFavFragmentInteractionListener {

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
    int pagerPosition = 0;
    boolean isPlaying = false;
    public static View.OnClickListener playListener;
    public static List<Song> songs = new ArrayList<>();
    List<PagerSwipeFragment> swapFragments = new ArrayList<>();
    private BottomNavigationView bottomNavigationView;
    private int mSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list_layout);

        if (songs == null || songs.size() == 0) {
            DemoSongList.fillDemoData(songs);
        }
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        setupBottomNav();
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

                    } else {
                        setViewPause(selectedSongName);
                    }
                }

            }
        };

        playPauseMainButt.setOnClickListener(playListener);
        playPauseBarButt.setOnClickListener(playListener);
        pagerSmallImage = findViewById(R.id.pager_image);

        setupViewPagerBar();
        //if got here from detail activity, set the same song view that was chosen
        if (getIntent().getExtras() != null) {
            int position = getIntent().getExtras().getInt(SONG_POSITION);
            viewPagerBar.setCurrentItem(position);
            setRecyclerViewPosition(position);

        }

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
        ((TextView) view).setTextColor(getResources().getColor(R.color.colorAccent));
        playPauseBarButt.setBackground(getResources().getDrawable(R.drawable.ic_pause));
        lastCheckedPosition = selectedSongPosition;
        //take reference to (view of) last song  played
        previouslySelectedSongName = selectedSongName;
    }

    public void setViewPause(View view) {
        isPlaying = false;
        playPauseMainButt.setText(R.string.play);
        ((TextView) view).setTextColor(getResources().getColor(R.color.white));
        playPauseBarButt.setBackground(getResources().getDrawable(R.drawable.ic_play_track_detail_16));

    }

    public void setupViewPagerBar() {
        viewPagerBar = findViewById(R.id.viewpager);
        for (Song song : songs) {
            PagerSwipeFragment swapFragment = PagerSwipeFragment.getInstance(songs.indexOf(song));
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
                setRecyclerViewPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setRecyclerViewPosition(final int position) {
        recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                recyclerView.findViewHolderForAdapterPosition(position).itemView.performClick();
                recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
        pagerPosition = position;
    }

    public void setupBottomNav() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectActivity(item);
                return true;
            }
        });
    }

    private void selectActivity(MenuItem item) {
        Fragment frag = null;
        // init corresponding fragment
        switch (item.getItemId()) {
            case R.id.menu_action_search:
                frag = LibraryArtistsFragment.newInstance();
                break;
            case R.id.menu_action_favorites:
                frag = FavoritesListFragment.newInstance();
                break;
            case R.id.menu_action_music:
                //(now playing)

                if (!(this.equals(SongListPlayerActivity.class))) {
                    Intent intent = new Intent(getApplicationContext(), SongListPlayerActivity.class);
                    startActivity(intent);
                }
                break;
        }

        mSelectedItem = item.getItemId();

        // uncheck the other items.
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            MenuItem menuItem = bottomNavigationView.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

        updateToolbarText(item.getTitle());

        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container, frag, frag.getTag());
            ft.commit();
        }
    }

    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFavFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }
}
