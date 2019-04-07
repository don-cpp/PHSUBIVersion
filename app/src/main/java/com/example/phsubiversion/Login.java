package com.example.phsubiversion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button SUDialog, Login;
    EditText username, password;
    DataSnapshot db;

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String uN = "123";
    private String pw = "123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseReference ref = database.getReference("Persons");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                db = dataSnapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        setContentView(R.layout.login_activity);
        username = findViewById(R.id.username_input);
        password = findViewById(R.id.password_input);
        SUDialog = findViewById(R.id.sign_up_button);
        Login = findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validUser(username.getText().toString(), password.getText().toString());
            }
        });
        SUDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpDialog.class);
                startActivity(intent);
            }
        });

    }


    public void validUser (String userName, String password)
    {
        DataSnapshot user = db.child(userName);
        if(user.child("password").getValue().toString().equals(password))
        {
            Intent intent = new Intent(getApplicationContext(),Dashboard.class);
            startActivity(intent);
        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = "Invalid login info";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void CreateUser(String userName, String password)
    {
        DatabaseReference ref = database.getReference("Persons");
        Persons user = new Persons(userName, password);
        ref.child(userName).setValue(user);
    }


}