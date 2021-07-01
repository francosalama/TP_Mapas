package com.example.tpmapas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentInicial extends Fragment {
    View layoutRoot;
    TextView tvNombre;
    EditText edNombre;
    Button btnEnviar;

    public FragmentInicial() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layoutRoot = inflater.inflate(R.layout.fragment_inicial, container, false);

        ObtenerReferencias();
        SetearListeners();

        return layoutRoot;
    }

    private void ObtenerReferencias(){
        tvNombre = (TextView) layoutRoot.findViewById(R.id.tvNombre);
        edNombre = (EditText) layoutRoot.findViewById(R.id.edNombre);
        btnEnviar = (Button) layoutRoot.findViewById(R.id.btnEnviar);
    }

    private void SetearListeners(){
        btnEnviar.setOnClickListener(btnEnviar_Click);
    }

    View.OnClickListener btnEnviar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nombre;
            MainActivity actividadContenedora;
            actividadContenedora = (MainActivity) getActivity();
            nombre = edNombre.getText().toString();
            assert actividadContenedora != null;
            actividadContenedora.IrAlFragmentJuego();
        }
    };
}