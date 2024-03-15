package com.mslaus.forestapp.helpers;

public class TimeHelper {

    private static String startingTime;
    private static String endTime;

    public static int time;

    public void setTime(int time) {
        TimeHelper.time = time;
    }

    public int getTime() {
        return time;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        TimeHelper.startingTime = startingTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        TimeHelper.endTime = endTime;
    }

}
