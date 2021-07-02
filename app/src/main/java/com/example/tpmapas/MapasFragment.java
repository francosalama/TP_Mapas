package com.example.tpmapas;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;


public class MapasFragment extends Fragment {

    private View        layoutRoot = null;
    private Button      btnTest01, btnTest02, btnTest03, btnTest04, btnTest05;
    private MapView     mMapView;
    private GoogleMap   googleMap;
    Resultado resultadoCiudades;
    TareaAsincronicaCiudades miTarea = new TareaAsincronicaCiudades();
    Type fooType = new TypeToken<ArrayList<Ciudades>>() {}.getType();
    ArrayList<Ciudades> listaCiudades = new ArrayList<Ciudades>();
    ArrayList<Ciudades> listaCiudadesRandom = new ArrayList<Ciudades>();
    Ciudades ciudadMapa = new Ciudades();
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


        miTarea.execute();

        //mMapView.getMapAsync(mMapView_getMapAsync);



        return layoutRoot;
    }

    private class TareaAsincronicaCiudades extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Estoy en el Main Thread.
        }

        @Override
        protected String doInBackground(Void... parametros) {
            HttpURLConnection miConexion = null;
            URL strAPIUrl; // Estoy en el Background Thread.
            BufferedReader responseReader;
            String responseLine, strResultado = "";
            StringBuilder sbResponse;

            try {
                strAPIUrl = new URL("https://api.polshu.com.ar/Data/geonames.json");
                miConexion = (HttpURLConnection) strAPIUrl.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    // En un BufferedReader leo todo!
                    responseReader = new BufferedReader(new InputStreamReader(miConexion.getInputStream()));
                    sbResponse = new StringBuilder();
                    while ((responseLine = responseReader.readLine()) != null) {
                        sbResponse.append(responseLine);
                    }
                    responseReader.close();
                    // Hasta aca lei la respuesta en el StringBuilder!
                    // La paso a la variable “strResultado”
                    strResultado = sbResponse.toString();
                } else {
                    // Ocurrió algún error.
                    Log.d("resultado", "hay error");
                }
            } catch (Exception e) {
                // Ocurrió algún error al conectarme, serán permisos?
                Log.d("resultado", "errores: " + e.getMessage());
            } finally {
                if (miConexion != null) {
                    miConexion.disconnect();
                }
            }
            Log.d("resultado", strResultado);
            return strResultado;
        }
        @Override protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);
            // Estoy en el Main Thread.
            Gson ciudades = new Gson();
            //resultadoCiudades = ciudades.fromJson(resultado,Resultado.class);
            listaCiudades = ciudades.fromJson(resultado,fooType);
            listaCiudadesRandom = obtenerCiudadesRandom();
            ciudadMapa = obtenerCiudadMapa();
            Log.d("random", listaCiudadesRandom.toString());
            Log.d("ciudades", listaCiudades.toString());
            Log.d("ciudadMapa", ciudadMapa.name);
            setearTextoBotones();
            mMapView.getMapAsync(mMapView_getMapAsync);
            //peliculasAdapter = new ArrayAdapter<Movies>(getActivity(), android.R.layout.simple_list_item_1, resultadoPeliculas.Search);
        }
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
            LatLng latLngCiudad = new LatLng(ciudadMapa.lat, ciudadMapa.lng);
            MarkerOptions markerSydney = new MarkerOptions()
                    .position(latLngCiudad)
                    .title("Ciudad random")
                    .snippet("Ciudad random");
            googleMap.addMarker (markerSydney);

            CameraPosition  cameraPosition;
            cameraPosition = new CameraPosition.Builder().target(latLngCiudad).zoom(15).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }
    };

    public ArrayList<Ciudades> obtenerCiudadesRandom(){
        ArrayList<Ciudades> listaCiudadesRandom = new ArrayList<Ciudades>();
        for(int i = 0; i < 4; i++){
            Random r = new Random();
            int pos = r.nextInt(listaCiudades.size());
            listaCiudadesRandom.add(listaCiudades.get(pos));
        }
        return listaCiudadesRandom;
    }

    public void setearTextoBotones(){
        btnTest01.setText(listaCiudadesRandom.get(0).name);
        btnTest02.setText(listaCiudadesRandom.get(1).name);
        btnTest03.setText(listaCiudadesRandom.get(2).name);
        btnTest04.setText(listaCiudadesRandom.get(3).name);
    }

    public Ciudades obtenerCiudadMapa(){
        Ciudades ciudadMapa;
        Random r = new Random();
        int pos = r.nextInt(listaCiudadesRandom.size());
        ciudadMapa = listaCiudadesRandom.get(pos);
        return ciudadMapa;
    }
}