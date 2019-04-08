package com.example.phsubiversion;
import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Persons {

    private String password;
    private String username;

    public Persons() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Persons(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}