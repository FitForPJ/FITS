package com.thpa.a9019.fitnessmore;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener,GoogleMap.OnMarkerDragListener{

    private GoogleMap mMap;
    private Marker m1;
    private Marker m2;
    private Marker m3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


//        CameraPosition cameraPosition = new CameraPosition(new LatLng(7.8927067,98.3779442), 13, 70, mMap.getCameraPosition().bearing);
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


         m1 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(7.894524, 98.3521533))
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("PSU"));
        m1.setTag(0);
        m1.setDraggable(true);

        m2 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(7.882894, 98.3948045))
                .icon(BitmapDescriptorFactory   // replace icon color
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                            // opacity
                .title("Club Asia Fitness Phuket"));
        m2.setTag(1);
        m2.setDraggable(true);

        m3 = mMap.addMarker(new MarkerOptions()
            .position(new LatLng(7.880791, 98.3716689))
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
            .alpha(0.7f)
            .title("Smile Gym"));
        m3.setTag(2);
        m3.setDraggable(true);


        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(7.894524, 98.3521533)).zoom(14).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Integer clickCount = (Integer) marker.getTag();
        if(clickCount.equals(0)){
            Intent intent = new Intent(MapActivity.this,GmapPSU.class);
            startActivity(intent);
        }

        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

        Toast.makeText(this, marker.getTitle() + " is starting dragged " , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        LatLng position = marker.getPosition();
        Toast.makeText(this, marker.getTitle() + " is ending dragged at " + position , Toast.LENGTH_SHORT).show();
    }
}
