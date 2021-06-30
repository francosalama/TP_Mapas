package com.example.tpmapas;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;


public class MapasFragment extends Fragment {

    private View        layoutRoot = null;
    private Button      btnTest01, btnTest02, btnTest03, btnTest04, btnTest05;
    private MapView     mMapView;
    private GoogleMap   googleMap;

    public MapasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (layoutRoot == null) {
            layoutRoot = inflater.inflate(R.layout.fragment_mapas, container, false);

            ObtenerReferencias();

            SetearListeners();

            mMapView.onCreate(savedInstanceState);
            mMapView.onResume(); // needed to get the map to display immediately

            try {
                MapsInitializer.initialize(getActivity().getApplicationContext());
            } catch (Exception e) {

            }
        }
        mMapView.getMapAsync(mMapView_getMapAsync);

        return layoutRoot;
    }

    private void ObtenerReferencias() {
        mMapView    = (MapView) layoutRoot.findViewById(R.id.mapView);
        btnTest01   = (Button) layoutRoot.findViewById(R.id.btnTest01) ;
        btnTest02   = (Button) layoutRoot.findViewById(R.id.btnTest02) ;
        btnTest03   = (Button) layoutRoot.findViewById(R.id.btnTest03) ;
        btnTest04   = (Button) layoutRoot.findViewById(R.id.btnTest04) ;
        //btnTest05   = (Button) layoutRoot.findViewById(R.id.btnTest05) ;
}

    private void SetearListeners() {
        //btnTest01.setOnClickListener(btnTest01_Click);
        //btnTest02.setOnClickListener(btnTest02_Click);
        //btnTest03.setOnClickListener(btnTest03_Click);
        //btnTest04.setOnClickListener(btnTest04_Click);
        //btnTest05.setOnClickListener(btnTest05_Click);
    }

    protected OnMapReadyCallback mMapView_getMapAsync = new OnMapReadyCallback() {
        @SuppressLint("MissingPermission")
        @Override
        public void onMapReady(GoogleMap mMap) {
            googleMap = mMap;
            LatLng latLngSydney = new LatLng(-33.852, 151.211);
            MarkerOptions markerSydney = new MarkerOptions()
                    .position(latLngSydney)
                    .title("Sydney Australia")
                    .snippet("Hay muchos canguros..");
            googleMap.addMarker (markerSydney);

            CameraPosition  cameraPosition;
            cameraPosition = new CameraPosition.Builder().target(latLngSydney).zoom(15).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }

        ;
    };
}