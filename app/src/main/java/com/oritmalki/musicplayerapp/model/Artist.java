package com.oritmalki.musicplayerapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user2 on 03/04/2018.
 */

public class Artist implements Serializable {

    private String artistName;
    private List<Song> songs;
    private List<Album> albums;
    private int artistImageUri;

    public Artist(String artistName, int artistImageUri) {
        this.artistName = artistName;
        this.artistImageUri = artistImageUri;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public int getArtistImageUri() {
        return artistImageUri;
    }

    public void setArtistImageUri(int artistImageUri) {
        this.artistImageUri = artistImageUri;
    }
}
