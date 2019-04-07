package com.example.phsubiversion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FindGame extends AppCompatActivity {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private List<String> gameInfo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_game);
        GetGameInfo();
    }

    public void GetGameInfo()
    {
        DatabaseReference ref = database.getReference("Games");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String entry = "Address:" + ds.child("address").getValue().toString() + '\n';
                    entry += "Sport: " + ds.child("sport").getValue().toString() + '\n';
                    entry += "Time: " + ds.child("time").getValue().toString();
                    gameInfo.add(entry);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
