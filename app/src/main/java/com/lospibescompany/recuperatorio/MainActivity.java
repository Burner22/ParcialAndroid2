package com.lospibescompany.recuperatorio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lospibescompany.recuperatorio.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MainActivityViewModel vm =
                new ViewModelProvider(this).get(MainActivityViewModel.class);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.cargarPersona(binding.nombre.getText()+"",
                        binding.edad.getText()+"",
                        binding.peso.getText()+"",
                        binding.altura.getText()+"",
                        binding.sexo.getSelectedItem()+""
                );
            }
        });
        vm.getError().observe(MainActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.error.setText(s);
            }
        });

    }


}