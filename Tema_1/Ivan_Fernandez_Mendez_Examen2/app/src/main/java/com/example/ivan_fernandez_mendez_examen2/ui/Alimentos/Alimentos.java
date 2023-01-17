package com.example.ivan_fernandez_mendez_examen2.ui.Alimentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ivan_fernandez_mendez_examen2.databinding.FragmentAlimentosBinding;

public class Alimentos extends Fragment {

    private FragmentAlimentosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AlimentosViewModel alimentosViewModel =
                new ViewModelProvider(this).get(AlimentosViewModel.class);

        binding = FragmentAlimentosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}