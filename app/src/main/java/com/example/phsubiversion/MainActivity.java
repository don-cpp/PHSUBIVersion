package com.example.phsubiversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switch_to_about(View view){
        setContentView(R.layout.about_activity);

    }

    public void switch_to_map(View view){
        //setContentView(R.layout.map_activity);
        //SWITCHING TO MAP_ACTIVITY CHANGE BY SHAH RUKH
        Intent intent = new Intent(this, Map_Activity.class);
        startActivity(intent);
    }


}
