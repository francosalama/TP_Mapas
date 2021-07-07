package com.example.tpmapas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class FragmentRanking extends Fragment {

    View layoutRoot;
    ArrayList<Ranking> listaJugadores = null;
    ListView lvRanking;
    ArrayAdapter<Ranking> rankingAdapter;


    public FragmentRanking() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layoutRoot = inflater.inflate(R.layout.fragment_ranking, container, false);

        ObtenerReferencias();
        rankingAdapter = new ArrayAdapter<Ranking>(getActivity(), android.R.layout.simple_list_item_1, listaJugadores);
        lvRanking.setAdapter(rankingAdapter);

        return layoutRoot;
    }

    private void ObtenerReferencias(){
        lvRanking = (ListView) layoutRoot.findViewById(R.id.lvRanking);
    }


    public void setearLista(ArrayList<Ranking> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }


}