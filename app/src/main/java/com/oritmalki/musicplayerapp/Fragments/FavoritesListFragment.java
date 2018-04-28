package com.oritmalki.musicplayerapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.oritmalki.musicplayerapp.Adapters.FavoritesAdapter;
import com.oritmalki.musicplayerapp.R;
import com.oritmalki.musicplayerapp.model.Song;

import java.util.ArrayList;
import java.util.List;

public class FavoritesListFragment extends Fragment {

    public static List<Song> favSongList = new ArrayList<>();
    private RecyclerView favRecyclerView;
    private FavoritesAdapter adapter;
    private Button backButton;


    //TODO complete this fragment, for the heart navView Item


    private OnFavFragmentInteractionListener mListener;

    public FavoritesListFragment() {
        // Required empty public constructor
    }

    public static FavoritesListFragment newInstance() {
        FavoritesListFragment fragment = new FavoritesListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library_fav, container, false);
        favRecyclerView = view.findViewById(R.id.fav_recyclerview);
        backButton = view.findViewById(R.id.fav_back_button);
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        adapter = new FavoritesAdapter(favSongList);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        favRecyclerView.setAdapter(adapter);
        favRecyclerView.setLayoutManager(lm);

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFavFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFavFragmentInteractionListener) {
            mListener = (OnFavFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFavFragmentInteractionListener");
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
    public interface OnFavFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFavFragmentInteraction(Uri uri);
    }
}
