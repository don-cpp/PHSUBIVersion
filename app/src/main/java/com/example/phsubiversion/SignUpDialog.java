package com.example.phsubiversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpDialog extends AppCompatActivity {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_dialog);
        DatabaseReference ref = database.getReference("Persons");
        Persons user = new Persons(findViewById(R.id.usernameS).toString(), findViewById(R.id.passwordS).toString());
        ref.child(findViewById(R.id.usernameS).toString()).setValue(user);

    }
}
