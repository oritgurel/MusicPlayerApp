package com.oritmalki.musicplayerapp.model;

import java.util.List;

/**
 * Created by user2 on 06/04/2018.
 */

public class DemoSongList {


    public static void fillDemoData(List<Song> songList) {
        songList.add(new Song("I love you", "The Beatles"));
        songList.add(new Song("Smells like teen spirit", "Nirvana"));
        songList.add(new Song("אהובתי", "אהוד בנאי"));
        songList.add(new Song("עכשיו מעונן", "אביב גפן"));
        songList.add(new Song("Loosing My Religion", "R.E.M"));
        songList.add(new Song("Spice Girls", "Wannabe"));
        songList.add(new Song("יזהר כהן", "אבניבי"));
        songList.add(new Song("ABBA", "Mamamia"));
        songList.add(new Song("Lou Reed", "Perfect Day"));
        songList.add(new Song("Green Day", "Basket Case"));
    }
}
