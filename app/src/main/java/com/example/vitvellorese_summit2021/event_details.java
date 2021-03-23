package com.example.vitvellorese_summit2021;

public class event_details {
    String title,icon,matter,date;
    public event_details()
    {

    }

    public event_details(String title, String icon, String matter, String date) {
        this.title = title;
        this.icon = icon;
        this.matter = matter;
        this.date = date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public String getIcon() {
        return icon;
    }

    public String getMatter() {
        return matter;
    }
}
