package com.example.repaso_examen_rec.ui.gestion.crearDietas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.repaso_examen_rec.MainActivity;
import com.example.repaso_examen_rec.R;
import com.example.repaso_examen_rec.databinding.FragmentCrearDietasBinding;

public class CrearDietas extends Fragment {

    private FragmentCrearDietasBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentCrearDietasBinding.inflate(inflater,container,false);
        Button boton=binding.bNavVerDietas;
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_crearDietas_to_verDiertas);
            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}