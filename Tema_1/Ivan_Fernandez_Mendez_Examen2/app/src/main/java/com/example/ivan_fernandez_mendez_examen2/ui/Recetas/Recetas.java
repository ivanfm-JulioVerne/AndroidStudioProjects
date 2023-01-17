package com.example.ivan_fernandez_mendez_examen2.ui.Recetas;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ivan_fernandez_mendez_examen2.ListenerReceta;
import com.example.ivan_fernandez_mendez_examen2.R;
import com.example.ivan_fernandez_mendez_examen2.databinding.FragmentRecetasBinding;

public class Recetas extends Fragment {

    private FragmentRecetasBinding binding;
    public ListenerReceta listener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RecetasViewModel galleryViewModel =
                new ViewModelProvider(this).get(RecetasViewModel.class);

        binding = FragmentRecetasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view=getView();

        Button bAlimento=(Button) view.findViewById(R.id.b_alimentos);

        bAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //listener es la interface que tienes en de atributo
                //y se comunica directamente con el el metodo
                //pulsaIncrementar del activity
                listener.pulsaAlimento();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ListenerReceta) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " Se debe implementar OnClickIncrementar");
        }

    }
}