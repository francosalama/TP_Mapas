package com.example.tpmapas;

import java.sql.Time;
import java.util.Timer;

public class Ranking {
    public int posicion;
    public String nombre;
    public int cantJugadasAcertadas;
    public Timer tiempoDeJuego;

    @Override
    public String toString() {
        return nombre;
    }
}
