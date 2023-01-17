package com.example.ivan_fernandez_mendez_examen2.ui.GestionDietas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ivan_fernandez_mendez_examen2.databinding.FragmentGestionDietasBinding;

public class GestionDietas extends Fragment {

    private FragmentGestionDietasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GestionDietasViewModel gestionDietasViewModel =
                new ViewModelProvider(this).get(GestionDietasViewModel.class);

        binding = FragmentGestionDietasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}