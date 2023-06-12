package com.example.ejercici013m5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ejercici013m5.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Button loadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        loadButton = binding.loadButton;
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.urlEditText.getText().toString();
                loadFragment(name);
            }
        });
    }

    private void loadFragment(String url) {
        // Ocultar el resto de los elementos en la actividad principal
        binding.urlEditText.setVisibility(View.GONE);
        loadButton.setVisibility(View.GONE);

        // Crear una instancia del fragmento que deseas cargar
        // y pasar la URL como argumento si es necesario
        Fragment fragment = WebFragment.newInstance(url);

        // Obtener el FragmentManager y comenzar la transacción
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Reemplazar el fragmento actual en el contenedor
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);

        // Agregar la transacción a la pila de retroceso
        fragmentTransaction.addToBackStack(null);

        // Confirmar la transacción
        fragmentTransaction.commit();
    }
}

