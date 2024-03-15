package com.mslaus.forestapp.objects;

public class TimeEvent {

    private String startTime;
    private String endTime;
    private int timeFocused;
    private String tag;

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getTimeFocused() {
        return timeFocused;
    }

    public String getTag() {
        return tag;
    }

    public TimeEvent(String startTime, String endTime, int timeFocused, String tag) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeFocused = timeFocused;
        this.tag = tag;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setTimeFocused(int timeFocused) {
        this.timeFocused = timeFocused;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
