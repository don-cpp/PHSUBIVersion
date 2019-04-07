package com.example.phsubiversion;

public class Games {
    public String address;
    public String ownerID;
    public String sport;
    public String time;



    public Games() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Games(String address, String ownerID, String sport, String time) {
        this.address = address;
        this.ownerID = ownerID;
        this.sport = sport;
        this.time = time;
    }
}
