package com.example.malami.przewodnikkulinarny;

public class restauracje {
    String nazwa;
    String dlugosc;
    String szerokosc;
    String adres;
    String id;

    restauracje()
    {

    }
    restauracje(String nazwa, String dlugosc, String szerokosc, String adres)
    {
        this.nazwa = nazwa;
        this.dlugosc =dlugosc;
        this.szerokosc=szerokosc;
        this.adres=adres;

    }

    public String getSzerokosc() {
        return szerokosc;
    }

    public String getAdres() {
        return adres;
    }

    public String getDlugosc() {
        return dlugosc;
    }

    public String getNazwa() {
        return nazwa;
    }
}
