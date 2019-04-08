package com.example.phsubiversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HostGame extends AppCompatActivity {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String strUN;
    Spinner sport;
    Button hostBack, hostConfirm;
    TextView address, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        strUN = getIntent().getStringExtra("username");
        setContentView(R.layout.activity_host_game);
        sport = findViewById(R.id.SportSelection);
        hostBack = findViewById(R.id.backHost);
        hostBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                startActivity(intent);
            }
        });
        hostConfirm = findViewById(R.id.confirmHost);
        hostConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = database.getReference("Games");
                Games game = new Games(address.getText().toString(), strUN, sport.getSelectedItem().toString(), time.getText().toString());
                ref.child(strUN).setValue(game);
            }
        });
        address = findViewById(R.id.etHostAddress);
        time = findViewById(R.id.hostTime);

    }
}