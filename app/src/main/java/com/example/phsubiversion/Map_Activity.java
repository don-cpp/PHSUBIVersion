package com.example.phsubiversion;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Map_Activity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    //@SuppressLint("MissingPermission")
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
            Toast.makeText(Map_Activity.this, "You have to accept to enjoy all app's services!", Toast.LENGTH_LONG).show();
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                map.setMyLocationEnabled(true);
            }}
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);

        // for Zoom Button Enable on Google Map
        map.getUiSettings().setZoomControlsEnabled(true);

        //for Location  Button enable on Google Map
        map.getUiSettings().setMyLocationButtonEnabled(true);



        final GoogleMap finalMap = map;
        finalMap.setMyLocationEnabled(true);
        finalMap.getUiSettings().setMyLocationButtonEnabled(true);
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                try {
                markerOptions.position(latLng);

                //finalMap.clear();



                    Toast.makeText(Map_Activity.this,geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0).getAddressLine(0) +" "+ geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0).getLocality() +" "+geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0).getAdminArea(),Toast.LENGTH_LONG).show();
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
              Toast.makeText(Map_Activity.this,"onMapClick:\n" + latLng.latitude + " : " +
                                                       latLng.longitude,Toast.LENGTH_LONG).show();
            }
        });



        for(int i=0;i<outerArr.size();i++){

            Double[] myString= new Double[3];
            myString=outerArr.get(i);
            try {
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(myString[0], myString[1]))
                        .title(geocoder.getFromLocation(myString[0], myString[1], 1).get(0).getAddressLine(0) +"\n"+ geocoder.getFromLocation(myString[0], myString[1], 1).get(0).getLocality() +"\n"+geocoder.getFromLocation(myString[0], myString[1], 1).get(0).getAdminArea()));
            } catch (IOException e) {
                e.printStackTrace();
            }
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
}