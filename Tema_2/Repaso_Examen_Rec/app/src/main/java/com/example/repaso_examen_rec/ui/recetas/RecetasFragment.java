package com.example.repaso_examen_rec.ui.recetas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.repaso_examen_rec.MainActivity;
import com.example.repaso_examen_rec.R;
import com.example.repaso_examen_rec.databinding.FragmentRecetasBinding;
import com.example.repaso_examen_rec.ui.alimentos.AlimentosFragment;
import com.example.repaso_examen_rec.ui.alimentos.ver_alimentos.VerAlimentosFragment;

public class RecetasFragment extends Fragment {

    private FragmentRecetasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RecetasViewModel recetasViewModel =
                new ViewModelProvider(this).get(RecetasViewModel.class);

        binding = FragmentRecetasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button bNavAlimento= binding.bNavAlimentos;
        bNavAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contenedor,new AlimentosFragment())
                        .commit();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}