package com.example.garbagecollector;

public class Credentials {


    private String username;
    private  String name;
    private  String password;
    private  String dateTime;

    private String location;

    public Credentials(String username, String name, String password, String dateTime , String location) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.dateTime = dateTime;
        this.location = location;
    }




    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }



}
