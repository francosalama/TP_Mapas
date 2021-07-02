package com.example.tpmapas;

public class Ciudades {
    public float lat;
    public float lng;
    public String name;
    public String clase;
    public String countrycode;
    public int population;

    @Override
    public String toString() {
        return name;
    }
}
