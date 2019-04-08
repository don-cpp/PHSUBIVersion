package com.example.phsubiversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dashboard extends AppCompatActivity {

    Button FindGame, HostGame, Logout;
    private String strUN;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        strUN = getIntent().getStringExtra("username");
        setContentView(R.layout.activity_dashboard);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Logout = findViewById(R.id.LogOutButton);
        FindGame = findViewById(R.id.FindGameButton);
        HostGame = findViewById(R.id.HostGameButton);
        HostGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HostGame.class);
                intent.putExtra("username", strUN);
                startActivity(intent);
            }
        });
        FindGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FindGame.class);
                startActivity(intent);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
}
