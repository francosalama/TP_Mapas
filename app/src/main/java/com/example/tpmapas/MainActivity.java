package com.example.tpmapas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MapasFragment fragmentMapas;
    FragmentInicial fragmentInicial;
    FragmentJuego fragmentJuego;
    FragmentRanking fragmentRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CrearFragments();
        IrAlFragmentInicial();
    }

    private void CrearFragments(){
        fragmentMapas = new MapasFragment();
        fragmentInicial = new FragmentInicial();
        fragmentJuego = new FragmentJuego();
        fragmentRanking = new FragmentRanking();
    }

    public void ReemplazarFragment(Fragment fragmento){
        FragmentManager manager;
        FragmentTransaction transaction;

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fraContenedor,fragmento,null);
        transaction.commit();
    }

    public void IrAlFragmentInicial(){
        ReemplazarFragment(fragmentInicial);
    }
    public void IrAlFragmentJuego(String nombre){
        fragmentJuego.setNombre(nombre);
        ReemplazarFragment(fragmentJuego);
    }
    public void IrAlFragmentRanking(ArrayList<Ranking> listaJugadores){
        fragmentRanking.setearLista(listaJugadores);
        ReemplazarFragment(fragmentRanking);
    }
    public void IrAlFragmentMapas(String nombre){
        fragmentMapas.setNombre(nombre);
        ReemplazarFragment(fragmentMapas);
    }
    public void EnviarMensaje(String nombre){
        //fragmentInicial.setMensaje(strMensaje);
    }
}