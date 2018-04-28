package com.oritmalki.musicplayerapp.model;

import java.util.List;

/**
 * Created by user2 on 03/04/2018.
 */

public class Album {

    private String albumTitle;
    private List<Song> albumSongs;
    private Artist artist;

    public Album(String albumTitle, Artist artist) {
        this.albumTitle = albumTitle;
        this.artist = artist;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public List<Song> getAlbumSongs() {
        return albumSongs;
    }

    public void setAlbumSongs(List<Song> albumSongs) {
        this.albumSongs = albumSongs;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
