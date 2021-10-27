package com.example.pruebafalsamapitas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pruebafalsamapitas.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Mapa extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    Button BTN;
    EditText CajonLatitud,CajonLonjitud, CajonTitulo;
    String origen, destino,fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        CajonLatitud = (EditText) findViewById(R.id.cajon_latitud);
        CajonLonjitud = (EditText) findViewById(R.id.cajon_lonjitud);
        CajonTitulo = (EditText) findViewById(R.id.titulo_marcador);
        BTN = (Button) findViewById((R.id.button2));

        Bundle b = getIntent().getExtras();
        origen = b.getString("SOrigen");
        destino = b.getString("SDestino");
        fecha = b.getString("SDate");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String miTitulo= CajonTitulo.getText().toString();
                double LAT = Double.parseDouble(CajonLatitud.getText().toString());
                double LON = Double.parseDouble(CajonLonjitud.getText().toString());
                LatLng Posicion = new LatLng(LAT,LON);
                mMap.addMarker(new MarkerOptions().position(Posicion).title(miTitulo));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(Posicion));

                double riallat = Double.parseDouble(CajonLatitud.getText().toString());
                double riallot = Double.parseDouble(CajonLonjitud.getText().toString());
                Intent I = new Intent(getApplicationContext(),Indique.class);
                I.putExtra("SOrigen", origen);
                I.putExtra("SDestino", destino);
                I.putExtra("SDate", fecha);
                I.putExtra("Slat", riallat);
                I.putExtra("Slot", riallot);
                startActivity(I);
            }
        });

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                CajonLatitud.setText(latLng.latitude+"");
                CajonLonjitud.setText(latLng.longitude+"");
            }
        });
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}