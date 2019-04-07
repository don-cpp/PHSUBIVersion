package com.example.phsubiversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpDialog extends AppCompatActivity {

    Button login;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login = findViewById(R.id.FinishSignUpButton);
        setContentView(R.layout.activity_sign_up_dialog);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = database.getReference("Persons");
                Persons user = new Persons(findViewById(R.id.usernameS).toString(), findViewById(R.id.passwordS).toString());
                ref.child(findViewById(R.id.usernameS).toString()).setValue(user);
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
}
