package com.oritmalki.musicplayerapp.model;

import java.util.List;

/**
 * Created by user2 on 03/04/2018.
 */

public class Artist {

    String artistName;
    List<Song> songs;
    List<Album> albums;
    String artistImageUri;

    public Artist(String artistName, String artistImageUri) {
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

    public String getArtistImageUri() {
        return artistImageUri;
    }

    public void setArtistImageUri(String artistImageUri) {
        this.artistImageUri = artistImageUri;
    }
}
