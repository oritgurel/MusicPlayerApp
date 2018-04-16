package com.oritmalki.musicplayerapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oritmalki.musicplayerapp.model.Artist;
import com.oritmalki.musicplayerapp.model.Song;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LibraryArtistsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LibraryArtistsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class LibraryArtistsFragment extends Fragment {

    List<Artist> artists;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private RecyclerView recyclerView;
    private List<Song> songs = new ArrayList<>(SongListPlayerActivity.songs);
    private ArtistsAdapter artistsAdapter;
    private BottomNavigationView bottomNavigationView;



    private OnFragmentInteractionListener mListener;

    public LibraryArtistsFragment() {
        // Required empty public constructor
    }

    public static LibraryArtistsFragment newInstance() {
        LibraryArtistsFragment fragment = new LibraryArtistsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        artists = new ArrayList<>();
        for (Song song : songs) {
            artists.add(song.getArtist());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library_artists, container, false);
        recyclerView = view.findViewById(R.id.artists_recyclerview);
        artistsAdapter = new ArtistsAdapter(artists);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(artistsAdapter);
        recyclerView.setLayoutManager(lm);

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
