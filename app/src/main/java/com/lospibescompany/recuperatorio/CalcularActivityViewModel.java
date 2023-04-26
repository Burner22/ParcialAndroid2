package com.lospibescompany.recuperatorio;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CalcularActivityViewModel extends AndroidViewModel {

    Context context;
    MutableLiveData<String> peso;
    MutableLiveData<Persona> per;

    public CalcularActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public void calcular (Bundle bundle){
        Persona persona = (Persona) bundle.getSerializable("per");
        per.setValue(persona);
        double calculo = persona.getPeso()/(persona.getAltura()*persona.getAltura());
        if(calculo < 20){
            peso.setValue("Usted esta por debajo del peso ideal");
        }
        else if(calculo >= 20 && calculo <=25){
            peso.setValue("Usted esta con el peso ideal");
        }
        else if(calculo > 25){
            peso.setValue("Usted esta por encima del peso ideal");
        }
    }

    public LiveData<Persona> getPersona(){
        if(per == null){
            per = new MutableLiveData<>();
        }
        return per;
    }

    public LiveData<String> getPeso(){
        if(peso == null){
            peso = new MutableLiveData<>();
        }
        return peso;
    }


}
