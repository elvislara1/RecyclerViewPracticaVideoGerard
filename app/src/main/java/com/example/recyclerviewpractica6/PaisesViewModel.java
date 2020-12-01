package com.example.recyclerviewpractica6;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PaisesViewModel extends AndroidViewModel {
    PaisRepository paisRepository;

    public PaisesViewModel(@NonNull Application application) {
        super(application);

        //se inicializa
        paisRepository = new PaisRepository();
    }

    //obtener lista de paises
    LiveData<List<Pais>> paises(){
        //retorna la lista del repository
        return paisRepository.getPaisesLiveData();
    }
}
