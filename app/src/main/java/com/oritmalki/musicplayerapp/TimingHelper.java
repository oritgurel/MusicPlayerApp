package com.oritmalki.musicplayerapp;

public class TimingHelper {

    //converts double timing values for int precentage

    public static int getTimingPrecentage(Double totalTiming, Double currentTiming) {
        Double precent = new Double(currentTiming / totalTiming) * 100;
        return precent.intValue();
    }
}
