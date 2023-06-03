package com.example.mapsapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

public class MapsActivity<distanceText> extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng Oliva = new LatLng(54.40405477794563, 18.570844056749248);
        LatLng UGlatLng = new LatLng(54.3967351202744, 18.574577692055026);
        MarkerOptions markerOptions = new MarkerOptions().position(UGlatLng).title("Uniwersytet Gdański");
        Marker marker1 = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(UGlatLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(UGlatLng,16f));
        MarkerOptions markerOptionss = new MarkerOptions().position(Oliva).title("Oliva Business Centre");
        Marker marker2 = mMap.addMarker(markerOptionss);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Oliva));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Oliva,16f));

        LatLng point1 = new LatLng(54.40405477794563, 18.570844056749248);
        LatLng point2 = new LatLng(54.3967351202744, 18.574577692055026);
        double distance = SphericalUtil.computeDistanceBetween(point1, point2);

        int padding = 100;
        String distanceText = String.format("%.2f m", distance);

        LatLng midpoint = new LatLng((point1.latitude + point2.latitude) / 2, (point1.longitude + point2.longitude) / 2);
        MarkerOptions markerOptionsA = new MarkerOptions()
                .position(midpoint)
                .title("Odległość")
                .snippet(distanceText);

        Marker marker = mMap.addMarker(markerOptionsA);


        PolylineOptions polylineOptions = new PolylineOptions()
                .add(point1, point2)
                .width(5)
                .color(Color.BLACK);

        Polyline polyline = mMap.addPolyline(polylineOptions);


        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(point1);
        builder.include(point2);
        LatLngBounds bounds = builder.build();
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));

    }



}