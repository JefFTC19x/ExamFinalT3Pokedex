package com.JeffTorres.primeraappv1;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.JeffTorres.primeraappv1.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private float latitud;
    private float longitud;

    private final static int GPS_REQUEST_CODE_PERMISSION= 10001;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        latitud = getIntent().getFloatExtra("latitud",0);
        longitud = getIntent().getFloatExtra("longitud",0);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //Preguntar y conceder permisos
        if(!PermisosGPS())
        {
        requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                },GPS_REQUEST_CODE_PERMISSION);
        }
    }

    private boolean PermisosGPS()
    {
        return  checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED
                &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng cajamarca = new LatLng(latitud,longitud);
        mMap.addMarker(new MarkerOptions().position(cajamarca).title("UBICACIÓN DE POKEMON"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cajamarca,20));
    }

//    @Override
//    public void onLocationChanged(@NonNull Location location)
//    {
//        if (mMap!=null)
//        {
//            LatLng localizar = new LatLng(location.getLatitude(), location.getLongitude());
//            mMap.addMarker(new MarkerOptions().position(localizar).title("Ubicación Actual de Jefferson"));
//
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localizar,/*Zoom ==> */4));
//
//        }
//    }
}