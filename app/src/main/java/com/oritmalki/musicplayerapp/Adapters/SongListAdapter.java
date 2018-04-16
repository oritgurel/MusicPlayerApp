package com.oritmalki.musicplayerapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oritmalki.musicplayerapp.R;
import com.oritmalki.musicplayerapp.Interfaces.SongListAdapterCallback;
import com.oritmalki.musicplayerapp.Activities.SongListPlayerActivity;
import com.oritmalki.musicplayerapp.model.Song;

import java.util.List;

/**
 * Created by user2 on 06/04/2018.
 */

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.SongHolder> {

    List<Song> songs;
    SongListAdapterCallback callback;

    public SongListAdapter(List<Song> songs, SongListAdapterCallback callback) {
        this.songs = songs;
        this.callback = callback;
    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song_list, parent, false);
        return new SongHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SongHolder holder, final int position) {

        holder.songName.setText(songs.get(position).getSongTitle());
        holder.artistName.setText(songs.get(position).getArtist().getArtistName());
        holder.timing.setText(String.valueOf(songs.get(position).getTiming()));
        holder.rowView.setOnClickListener(new OnClickListener() {

            //get info from selected item
            @Override
            public void onClick(View v) {
                callback.onSongListItemSelected(holder.songName, position);
                SongListPlayerActivity.playListener.onClick(v);
            }
        });
        holder.favIc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (v.isPressed())
                //TODO create animation when an item is clicked as feedback for user
            }
        });
    }


    @Override
    public int getItemCount() {
        return songs.size();
    }


    static class SongHolder extends RecyclerView.ViewHolder {
        TextView songName;
        TextView artistName;
        TextView timing;
        ImageView favIc;
        ViewGroup rowView;


        public SongHolder(View itemView) {
            super(itemView);

            songName = itemView.findViewById(R.id.song_name);
            artistName = itemView.findViewById(R.id.artist_name);
            timing = itemView.findViewById(R.id.song_timing);
            favIc = itemView.findViewById(R.id.add_favorite);
            rowView = itemView.findViewById(R.id.row_view);

        }
    }
}
