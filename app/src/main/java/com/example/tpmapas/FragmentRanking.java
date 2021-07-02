package com.example.tpmapas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class FragmentRanking extends Fragment {

    View layoutRoot;
    String nombre = null;
    ListView lvRanking;


    public FragmentRanking() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layoutRoot = inflater.inflate(R.layout.fragment_ranking, container, false);

        ObtenerReferencias();

        return layoutRoot;
    }

    private void ObtenerReferencias(){
        lvRanking = (ListView) layoutRoot.findViewById(R.id.lvRanking);
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}