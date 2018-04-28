package com.oritmalki.musicplayerapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oritmalki.musicplayerapp.Adapters.FavoritesAdapter.FavoritesHolder;
import com.oritmalki.musicplayerapp.R;
import com.oritmalki.musicplayerapp.model.Song;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesHolder> {

    List<Song> favoritesList;

    public FavoritesAdapter(List<Song> favoritesList) {
        this.favoritesList = favoritesList;
    }

    @NonNull
    @Override
    public FavoritesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav_list, parent, false);
        return new FavoritesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesHolder holder, int position) {
        holder.artistName.setText(favoritesList.get(position).getArtist().getArtistName());
        holder.songName.setText(favoritesList.get(position).getSongTitle());
    }

    @Override
    public int getItemCount() {
        return favoritesList.size();
    }

    static class FavoritesHolder extends ViewHolder {

        TextView songName;
        TextView artistName;
        ImageView removeFromList;

        public FavoritesHolder(View itemView) {
            super(itemView);
            artistName = itemView.findViewById(R.id.artist_name_fav);
            songName = itemView.findViewById(R.id.song_name_fav);
            removeFromList = itemView.findViewById(R.id.cancel_favorite);

        }
    }
}
