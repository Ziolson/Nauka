package com.example.ziolson.hikerswatch;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    String provider;

    TextView latTV;
    TextView lngTV;
    TextView altTV;
    TextView bearingTV;
    TextView speedTV;
    TextView accuracyTV;
    TextView addressTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latTV = (TextView)findViewById(R.id.latTextView);
        lngTV = (TextView) findViewById(R.id.lngTextView);
        altTV = (TextView) findViewById(R.id.altTextView);
        bearingTV = (TextView) findViewById(R.id.bearingTextView);
        speedTV = (TextView) findViewById(R.id.speedTextView);
        accuracyTV = (TextView) findViewById(R.id.accuracyTextView);
        addressTV = (TextView) findViewById(R.id.addressTextView);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        Location location = locationManager.getLastKnownLocation(provider);
        onLocationChanged(location);
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Double lat = location.getLatitude();
        Double lng = location.getLongitude();
        Double alt = location.getAltitude();
        Float bearing = location.getBearing();
        Float speed = location.getSpeed();
        Float accuracy = location.getAccuracy();

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> listAddresses = geocoder.getFromLocation(lat, lng, 1);
            if (listAddresses != null && listAddresses.size() > 0) {
                Log.i("PlaceInfo", listAddresses.get(0).toString());

                String addressHolder = "";

                for (int i = 0; i <= listAddresses.get(0).getMaxAddressLineIndex(); i++) {
                    addressHolder += listAddresses.get(0).getAddressLine(i) + "\n";
                }

                addressTV.setText("Address: \n" + addressHolder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        latTV.setText("Latitude: " + lat.toString());
        lngTV.setText("Longitude: " + lng.toString());
        altTV.setText("Altitude: " + alt.toString() + "m");
        bearingTV.setText("Bearing: " + bearing.toString());
        speedTV.setText("Speed: " + speed.toString() + "m/s");
        accuracyTV.setText("Accuracy: " + accuracy.toString() + "m");


        Log.i("Latitude", String.valueOf(lat));
        Log.i("Longitude", lng.toString());
        Log.i("Altitude", String.valueOf(alt));
        Log.i("Bearing", String.valueOf(bearing));
        Log.i("Speed", String.valueOf(speed));
        Log.i("Accuracy", String.valueOf(accuracy));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
