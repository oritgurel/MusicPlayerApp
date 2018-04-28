package com.oritmalki.musicplayerapp.model;

import java.io.Serializable;

/**
 * Created by user2 on 03/04/2018.
 */

public class Song implements Serializable {

    private String songTitle;
    private Artist artist;
    private double timing;
    private int imgUri;
    private String path;
    private boolean isFav;

    public Song(String title, Artist artist, int imgUri) {
        this.songTitle = title;
        this.artist = artist;
        this.imgUri = imgUri;
    }

    public Song(String title, Artist artist) {
        this.songTitle = title;
        this.artist = artist;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public double getTiming() {
        return timing;
    }

    public void setTiming(double timing) {
        this.timing = timing;
    }

    public int getImgUri() {
        return imgUri;
    }

    public void setImgUri(int imgUri) {
        this.imgUri = imgUri;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}
