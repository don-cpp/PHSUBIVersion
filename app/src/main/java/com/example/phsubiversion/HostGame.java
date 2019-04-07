package com.example.phsubiversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HostGame extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        writeNewUser("address3", "7", "soccer", "3:00 am");
    }

    private void writeNewUser(String address, String ownerID, String sport, String time) {
        Games game = new Games(address, ownerID, sport, time);

        mDatabase.child("Games").child(ownerID + sport).setValue(game);
    }
}