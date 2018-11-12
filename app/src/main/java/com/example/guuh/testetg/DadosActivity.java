package com.example.guuh.testetg;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DadosActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        ActivityCompat.OnRequestPermissionsResultCallback {
public GoogleMap mapa;
public SupportMapFragment mapFragment;
public GoogleApiClient mGoogleApiClient;
public EditText local;
public EditText address;
public Button btgps;
public LatLng localizacao = new LatLng(-23.578023, -48.032134);


@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.nossoMapa);


        mapFragment.getMapAsync(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(LocationServices.API)
        .build();
        botoes();

        }

private void botoes() {
        btgps = (Button) findViewById(R.id.btnPosicao);
        btgps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        if (ActivityCompat.checkSelfPermission(DadosActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(DadosActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                        }
                        mapa.setMyLocationEnabled(true);

                        Location getL = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                        LatLng Posicao = new LatLng(getL.getLatitude(), getL.getLongitude());
                        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(Posicao, 16);
                        mapa.animateCamera(update);
                        Log.d("Bt", "foi");

                        mapa.addMarker(new MarkerOptions().position(Posicao).title("HELP ME!!!!"));
                }
        });
}
@Override
public void onConnected(@Nullable Bundle bundle) {

}

@Override
public void onConnectionSuspended(int i) {

}

@Override
protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
}
@Override
public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

}

@Override
public void onMapReady(GoogleMap googleMap) {
        this.mapa = googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(localizacao, 18);
        mapa.animateCamera(update);
        Log.d("Mapa","foi");

        mapa.addMarker(new MarkerOptions().position(localizacao).title("Polícia"));

        }
}
