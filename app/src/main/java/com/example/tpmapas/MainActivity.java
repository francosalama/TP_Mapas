package com.example.tpmapas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MapasFragment fragmentMapas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CrearFragments();
        IrAlFragmentMapas();
    }

    private void CrearFragments(){
        fragmentMapas = new MapasFragment();
    }

    public void ReemplazarFragment(Fragment fragmento){
        FragmentManager manager;
        FragmentTransaction transaction;

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fraContenedor,fragmento,null);
        transaction.commit();
    }

    public void IrAlFragmentMapas(){
        ReemplazarFragment(fragmentMapas);
    }
}