package com.example.tpmapas;

import java.sql.Time;
import java.util.Timer;

public class Ranking {
    //public int posicion;
    public String nombre;
    public int cantJugadasAcertadas;
    public String tiempo;

    @Override
    public String toString() {
        return nombre + " " +  cantJugadasAcertadas + " "  + tiempo;
    }
}
