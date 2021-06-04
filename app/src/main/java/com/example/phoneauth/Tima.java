package com.example.phoneauth;

public class Tima {
    private int hour;
    private int minute;

    public String setTime(int hour,int minute){
        String time = hour +":"+ minute;
        return time;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
