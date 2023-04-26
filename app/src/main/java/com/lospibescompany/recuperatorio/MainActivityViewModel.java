package com.lospibescompany.recuperatorio;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.nfc.FormatException;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    public MutableLiveData<Persona> persona;
    public MutableLiveData<String> error;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public void cargarPersona(String nombre, String edad, String peso, String altura, String sexo){
        boolean pes = isNumeric(peso);
        boolean alt = isNumeric(altura);
        if(peso.length() != 0 && altura.length() != 0 && edad.length() != 0 && nombre.length() != 0 && pes && alt){
            double pesoPer = Double.valueOf(peso);
            double alturaPer = Double.valueOf(altura);
            Persona per = new Persona(nombre,edad,sexo,alturaPer,pesoPer);
            Bundle bundle = new Bundle();
            bundle.putSerializable("per", per);
            Toast.makeText(context,"funciona",Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, CalcularActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtras(bundle);
            context.startActivity(i);

        }
        else{
            error.setValue("Se han ingresado datos invalidos");
        }
    }

    public static boolean isNumeric(String cadena) {
        boolean resultado;
        try {
            Double.parseDouble(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }

    public MutableLiveData getError(){
        if(error == null){
            error = new MutableLiveData<>();
        }
        return error;
    }

}
