package com.thpa.a9019.fitnessmore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mapView;
    View mView;
    private Marker m1;
    private Marker m2;
    private Marker m3;

    public MapFragment() {

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) mView.findViewById(R.id.frmap);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        return mView;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        m1 = mGoogleMap.addMarker(new MarkerOptions()
                .position(new LatLng(7.894524, 98.3521533))
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("PSU"));
        m1.setTag(0);
        m1.setDraggable(true);

        m2 = mGoogleMap.addMarker(new MarkerOptions()
                .position(new LatLng(7.882894, 98.3948045))
                .icon(BitmapDescriptorFactory   // replace icon color
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                // opacity
                .title("Club Asia Fitness Phuket"));
        m2.setTag(1);
        m2.setDraggable(true);

        m3 = mGoogleMap.addMarker(new MarkerOptions()
                .position(new LatLng(7.880791, 98.3716689))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                .alpha(0.7f)
                .title("Smile Gym"));
        m3.setTag(2);
        m3.setDraggable(true);

        CameraPosition cameraPosition = new CameraPosition(new LatLng(7.8927067,98.3779442), 13, 70, mGoogleMap.getCameraPosition().bearing);
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getTag().equals(0)) {
                    Intent intent = new Intent(getContext(), GmapPSU.class);
                    startActivity(intent);

                }
                return true;
            }
        });
    }


}
