package com.oritmalki.musicplayerapp.model;

import com.oritmalki.musicplayerapp.R;

import java.util.List;

/**
 * Created by user2 on 06/04/2018.
 */

public class DemoSongList {


    public static void fillDemoData(List<Song> songList) {
        songList.add(new Song("I love you", new Artist("The Beatles", R.drawable.the_beatles)));
        songList.add(new Song("Smells like teen spirit", new Artist("Nirvana", R.drawable.nirvana)));
        songList.add(new Song("אהובתי", new Artist("אהוד בנאי", R.drawable.ehud_banai)));
        songList.add(new Song("עכשיו מעונן", new Artist("אביב גפן", R.drawable.aviv_geffen)));
        songList.add(new Song("Loosing My Religion", new Artist("R.E.M", R.drawable.rem)));
        songList.add(new Song("Wannabe", new Artist("Spice Girls", R.drawable.spice_girls)));
        songList.add(new Song("אבניבי", new Artist("יזהר כהן", R.drawable.abanibi)));
        songList.add(new Song("Mamamia", new Artist("ABBA", R.drawable.abba)));
        songList.add(new Song("Perfect Day", new Artist("Lou Reed", R.drawable.lou_reed)));
        songList.add(new Song("Basket Case", new Artist("GreenDay", R.drawable.greenday)));
    }
}
