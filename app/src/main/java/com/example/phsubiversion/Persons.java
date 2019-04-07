package com.example.phsubiversion;
import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Persons {

    public int age;
    public String fname;
    public String lname;
    private String password;
    public String sex;
    private String username;

    public Persons() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Persons(int age, String fname, String lname, String sex, String username, String password) {
        this.fname = fname;
        this.age = age;
        this.lname = lname;
        this.sex = sex;
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