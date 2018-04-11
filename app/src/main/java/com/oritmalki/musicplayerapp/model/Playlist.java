package com.oritmalki.musicplayerapp.model;

import java.util.List;

/**
 * Created by user2 on 03/04/2018.
 */

public class Playlist {

    List<Song> playlistSongs;

    public Playlist(List<Song> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }

    public List<Song> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(List<Song> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }
}
