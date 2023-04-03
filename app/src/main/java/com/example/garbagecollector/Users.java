package com.example.garbagecollector;
import java.util.Date;

public class Users {
    private String username;
    private String name;
    private String password;
    private Date dateTime;
    private String location;

    public Users(String username, String name, String password, Date dateTime, String location) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.dateTime = dateTime;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
