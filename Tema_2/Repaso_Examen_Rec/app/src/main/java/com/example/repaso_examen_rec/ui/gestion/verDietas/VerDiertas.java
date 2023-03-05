package com.example.repaso_examen_rec.ui.gestion.verDietas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.repaso_examen_rec.R;
import com.example.repaso_examen_rec.databinding.FragmentVerDiertasBinding;

public class VerDiertas extends Fragment {

    private FragmentVerDiertasBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentVerDiertasBinding.inflate(inflater,container,false);
        Button boton=binding.bNavCrearDieta;
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_verDiertas_to_crearDietas);
            }
        });

        return binding.getRoot();
    }
}