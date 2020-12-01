package com.example.recyclerviewpractica6;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;

public class PaisRepository {

    LiveData<List<Pais>> paisesLiveData;

    PaisRepository(){
        List<Pais> paises = Arrays.asList(
                new Pais("FRANCIA", "2018", R.drawable.fr2018),
                new Pais("ALEMANIA", "2014", R.drawable.ale2014),
                new Pais("ESPAÑA", "2010", R.drawable.esp2010),
                new Pais("ITALIA", "2006", R.drawable.ita2006),
                new Pais("BRASIL", "2002", R.drawable.br2002),
                new Pais("FRANCIA", "1998", R.drawable.fr1998),
                new Pais("BRASIL", "1994", R.drawable.br1994),
                new Pais("ALEMANIA", "1990", R.drawable.ale1990),
                new Pais("ARGENTINA", "1986", R.drawable.arg1986)
        );
        paisesLiveData = new MutableLiveData<>(paises);
    }
    LiveData<List<Pais>> getPaisesLiveData(){
        //cuando llame a getPaiseLiveData me retornara el liveData con toda la lista de paises
        // que quiero mostrar
        return paisesLiveData;
    }
}
