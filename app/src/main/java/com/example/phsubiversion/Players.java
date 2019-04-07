package com.example.phsubiversion;

public class Players {

    public String GameID;
    public String PersonID;


    public Players() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Players(String GameID, String PersonID) {
        this.GameID = GameID;
        this.PersonID = PersonID;
    }
}
