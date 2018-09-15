package com.example.workoutapp.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.workoutapp.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final int PLACE_CODE = 10;
    public static final String PLACE_TAG = "PLACE_TAG";
    private GoogleMap mMap;
    private static final int REQUEST_CODE = 20;
    private String[] myPermissions = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    String place = "";
    Button btonConfirmPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btonConfirmPlace = findViewById(R.id.btonConfirmPlace);
        btonConfirmPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(place.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Potrebno odabrati lokaciju.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent resultIntent = new Intent();
                resultIntent.putExtra(PLACE_TAG, place);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        if(!isDeviceOnline(this)) {
            Toast.makeText(this, "Potrebno se spojiti na Internet.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.e(this.getClass().getSimpleName(), "Permission failed");
            ActivityCompat.requestPermissions(this, myPermissions, REQUEST_CODE);

            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                if(!isLocationEnabled(getApplicationContext())){
                    Toast.makeText(getApplicationContext(),"Uključite lokaciju.",Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                findByClick(latLng);
            }
        });
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    public static boolean isDeviceOnline(Context context) {
        boolean isConnectionAvail = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if(netInfo != null)
                return netInfo.isConnected();
            else
                return isConnectionAvail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConnectionAvail;
    }

    private void findByClick(LatLng latLng){

        Address address = null;
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());
            mMap.clear();
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            address = addresses.get(0);
            mMap.addMarker(new MarkerOptions().position(latLng).title("Kliknuta lokacija"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
            if(address.getLocality() != null)
                place = address.getLocality()
                    + ", " + address.getCountryName().toString();
            else
                place = address.getCountryName().toString();
            Toast.makeText(getApplicationContext()," Pronađena lokacija: " + place, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
