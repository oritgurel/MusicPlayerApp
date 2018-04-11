package com.oritmalki.musicplayerapp.model;

/**
 * Created by user2 on 03/04/2018.
 */

public class Song {

    String songTitle;
    String artistName;
    double timing;
    String imgUri;
    String path;

    public Song(String title, String artistName) {
        this.songTitle = title;
        this.artistName = artistName;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public double getTiming() {
        return timing;
    }

    public void setTiming(double timing) {
        this.timing = timing;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
