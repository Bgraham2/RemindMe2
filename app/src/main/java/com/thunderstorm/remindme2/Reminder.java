package com.thunderstorm.remindme2;

public class Reminder {
    private String title;
    private String days;
    private String time;

    public Reminder(String t, String d, String hm) {
        title = t;
        days = d;
        time = hm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
