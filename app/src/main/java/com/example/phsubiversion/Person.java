package com.example.phsubiversion;
import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Person {

    public int age;
    public String fname;

    public Person() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Person(String fname, int age) {
        this.fname = fname;
        this.age = age;
    }

}