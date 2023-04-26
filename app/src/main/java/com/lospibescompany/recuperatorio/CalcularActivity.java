package com.lospibescompany.recuperatorio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lospibescompany.recuperatorio.databinding.ActivityCalcularBinding;
import com.lospibescompany.recuperatorio.databinding.ActivityMainBinding;


public class CalcularActivity extends AppCompatActivity {

    ActivityCalcularBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalcularBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        CalcularActivityViewModel vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(CalcularActivityViewModel.class);
        Bundle bundle = getIntent().getExtras();

        vm.getPeso().observe(CalcularActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.calculo.setText(s);
            }
        });
        vm.getPersona().observe(CalcularActivity.this, new Observer<Persona>() {
            @Override
            public void onChanged(Persona persona) {
                binding.persona.setText("Nombre: "+persona.getNombre()+
                        "\n Edad: "+persona.getEdad()+
                        "\n Altura: "+persona.getAltura()+
                         "\n Peso: "+persona.getPeso()+
                         "\n Sexo: "+persona.getSexo());
            }
        });
        vm.calcular(bundle);


    }
}