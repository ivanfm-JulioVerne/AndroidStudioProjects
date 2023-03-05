package com.example.repaso_examen_rec.ui.gestion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.repaso_examen_rec.databinding.FragmentGestionBinding;

public class GestionFragment extends Fragment {

    private FragmentGestionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GestionViewModel slideshowViewModel =
                new ViewModelProvider(this).get(GestionViewModel.class);

        binding = FragmentGestionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}