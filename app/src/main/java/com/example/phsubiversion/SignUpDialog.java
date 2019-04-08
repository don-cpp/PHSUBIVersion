package com.example.phsubiversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpDialog extends AppCompatActivity {

    Button login;
    EditText username, password;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_dialog);
        username = findViewById(R.id.usernameS);
        password = findViewById(R.id.passwordS);
        login = findViewById(R.id.FinishSignUpButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = database.getReference("Persons");
                Persons user = new Persons(username.getText().toString(), password.getText().toString());
                ref.child(username.getText().toString()).setValue(user);
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
}
