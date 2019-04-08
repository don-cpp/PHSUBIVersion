package com.example.phsubiversion;

public class Games {

    private String address;
    private String ownerID;
    private String sport;
    private String time;



    public Games() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Games(String address, String ownerID, String sport, String time) {
        this.address = address;
        this.ownerID = ownerID;
        this.sport = sport;
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
