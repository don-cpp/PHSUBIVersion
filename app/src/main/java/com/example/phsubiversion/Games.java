package com.example.phsubiversion;

public class Games {
    public String stAddress;
    public int zipcode;
    public String city;
    public String ownerID;
    public int minPlayers;
    public int maxPlayers;
    public String sport;
    public String description;


    public Games() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Games(String stAddress, int zipcode, String city, String ownerID, int minPlayers, int maxPlayers, String sport, String description) {
        this.stAddress = stAddress;
        this.zipcode = zipcode;
        this.city = city;
        this.ownerID = ownerID;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.sport = sport;
        this.description = description;
    }
}
