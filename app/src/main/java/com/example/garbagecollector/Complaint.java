package com.example.garbagecollector;
public class Complaint {
    private String name;
    private String contact;
    private String complaint;

    public Complaint() {}

    private String place;

    public Complaint(String name, String contact, String complaint, String place) {
        this.name = name;
        this.contact = contact;
        this.complaint = complaint;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}

