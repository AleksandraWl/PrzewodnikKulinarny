package com.example.malami.przewodnikkulinarny;

/**
 * Created by MalaMi on 19.09.2018.
 */

public class kategorie {
    String kategoria;
    String informacja;

    kategorie(){

    }

    public kategorie(String kategoria, String informacja) {
        this.kategoria = kategoria;
        this.informacja=informacja;
    }

    public String getKategoria() {
        return kategoria;
    }

    public String getInformacja(){
        return informacja;
    }
}
