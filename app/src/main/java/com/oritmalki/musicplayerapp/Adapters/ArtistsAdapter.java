package com.oritmalki.musicplayerapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oritmalki.musicplayerapp.R;
import com.oritmalki.musicplayerapp.model.Artist;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistHolder> {

    List<Artist> artists;

    public ArtistsAdapter(List<Artist> artists) {
        this.artists = artists;
    }

    @NonNull
    @Override
    public ArtistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_list_item, parent, false);
        return new ArtistHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistHolder holder, int position) {
        holder.artistName.setText(artists.get(position).getArtistName());
        holder.artistImage.setImageResource(artists.get(position).getArtistImageUri());
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    static class ArtistHolder extends ViewHolder {

        CircleImageView artistImage;
        TextView artistName;

        public ArtistHolder(View itemView) {
            super(itemView);
            artistImage = itemView.findViewById(R.id.artist_image_list);
            artistName = itemView.findViewById(R.id.artist_name_list);

        }
    }
}
