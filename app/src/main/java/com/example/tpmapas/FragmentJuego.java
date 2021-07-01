package com.example.tpmapas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FragmentJuego extends Fragment {
    View layoutRoot;
    TextView tvObjetivos;
    Button btnRanking;
    Button btnComenzar;

    public FragmentJuego() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layoutRoot = inflater.inflate(R.layout.fragment_juego, container, false);

        ObtenerReferencias();
        SetearListeners();

        return layoutRoot;
    }

    private void ObtenerReferencias(){
        tvObjetivos = (TextView) layoutRoot.findViewById(R.id.tvObjetivos);
        btnComenzar = (Button) layoutRoot.findViewById(R.id.btnComienzo);
        btnRanking = (Button) layoutRoot.findViewById(R.id.btnRanking);
    }

    private void SetearListeners(){
        btnComenzar.setOnClickListener(btnComenzar_Click);
        btnRanking.setOnClickListener(btnRanking_Click);
    }

    View.OnClickListener btnComenzar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity actividadContenedora;
            actividadContenedora = (MainActivity) getActivity();
            assert actividadContenedora != null;
            actividadContenedora.IrAlFragmentMapas();
        }
    };

    View.OnClickListener btnRanking_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity actividadContenedora;
            actividadContenedora = (MainActivity) getActivity();
            assert actividadContenedora != null;
            actividadContenedora.IrAlFragmentRanking();
        }
    };
}