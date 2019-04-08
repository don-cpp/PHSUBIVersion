package com.example.phsubiversion;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FindGame extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener{

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("Games");
    public final List<String> gameInfo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_find_game);

        setContentView(R.layout.activity_find_game);
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
        setContentView(R.layout.map_activity);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap map) {
        ArrayList<Double[]> outerArr = new ArrayList<Double[]>();
        Double[] myString1= {39.0997,-94.5786};
        outerArr .add(myString1);
        Double[] myString2= {39.6997,-94.5786};
        outerArr .add(myString2);
        Double[] myString3= {39.9997,-94.5786};
        outerArr .add(myString3);

        final Geocoder geocoder;
        geocoder = new Geocoder(this, Locale.getDefault());



        map.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        } else {
            Toast.makeText(FindGame.this, "You have to accept to enjoy all app's services!", Toast.LENGTH_LONG).show();
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                map.setMyLocationEnabled(true);
            }}
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);

        // for Zoom Button Enable on Google Map
        map.getUiSettings().setZoomControlsEnabled(true);

        //for Location  Button enable on Google Map
        map.getUiSettings().setMyLocationButtonEnabled(true);



        final GoogleMap finalMap = map;
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                try {
                    markerOptions.position(latLng);

                    //finalMap.clear();



                    Toast.makeText(FindGame.this,geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0).getAddressLine(0) +" "+ geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0).getLocality() +" "+geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0).getAdminArea(),Toast.LENGTH_LONG).show();
                    markerOptions.title(geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0).getAddressLine(0) +"\n"+ geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0).getLocality() +"\n"+geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0).getAdminArea());
                    finalMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    finalMap.addMarker(markerOptions);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                //finalMap.clear();
                finalMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                //finalMap.addMarker(markerOptions);
                Toast.makeText(FindGame.this,"onMapClick:\n" + latLng.latitude + " : " +
                        latLng.longitude,Toast.LENGTH_LONG).show();
            }
        });



        /*for(int i=0;i<outerArr.size();i++){

            Double[] myString= new Double[3];
            myString=outerArr.get(i);
            try {
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(myString[0], myString[1]))
                        .title(geocoder.getFromLocation(myString[0], myString[1], 1).get(0).getAddressLine(0) +"\n"+ geocoder.getFromLocation(myString[0], myString[1], 1).get(0).getLocality() +"\n"+geocoder.getFromLocation(myString[0], myString[1], 1).get(0).getAdminArea()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        for(int i=0;i<gameInfo.size();i++) {

            String myString;
            myString = gameInfo.get(i);

                int height = 100;
                int width = 100;
            BitmapDrawable bitmapdraw;
                if (myString.split("\n")[1].toLowerCase().contains("football")) {
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.football);
                }
                else if (myString.split("\n")[1].toLowerCase().contains("soccer")) {
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.soccer);
                }
                else if (myString.split("\n")[1].toLowerCase().contains("basketball")) {
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.basketball);
                }
                else if (myString.split("\n")[1].toLowerCase().contains("baseball")) {
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.baseball);
                }
                else if (myString.split("\n")[1].toLowerCase().contains("golf")) {
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.golf);
                }
                else{
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.tennis);
                }
                Bitmap b=bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                Toast.makeText(FindGame.this, myString,Toast.LENGTH_LONG).show();
                Address temp;
                try{
                    temp=geocoder.getFromLocationName(myString.split("\n")[0], 5).get(0);
                }catch (Exception e1)
                {
                    e1.printStackTrace();
                    continue;
                }

                map.addMarker(new MarkerOptions()
                        .position(new LatLng(temp.getLatitude(), temp.getLongitude()))
                        .title(myString).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        }


        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(39.0997, -94.5786))
                .zoom(10)
                .bearing(0)
                .tilt(45)
                .build();


//        map.moveCamera(CameraUpdateFactory.newCameraPosition(googlePlex));
        map.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, marker.getTitle(),
                Toast.LENGTH_SHORT).show();
    }
    ///////////////////////////////////////////////////////////////////////////
}
