package com.example.vitvellorese_summit2021;

public class speakers_details {
    String  name,photo,position;
    public  speakers_details()
    {

    }
    public speakers_details(String name, String photo, String position) {
        this.name = name;
        this.photo = photo;
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String photo) {
        this.photo = photo;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPosition() {
        return position;
    }

}
