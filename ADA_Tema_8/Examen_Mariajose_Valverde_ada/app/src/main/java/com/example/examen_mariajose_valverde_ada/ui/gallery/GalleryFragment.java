package com.example.examen_mariajose_valverde_ada.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.examen_mariajose_valverde_ada.databinding.FragmentGalleryBinding;
import com.example.examen_mariajose_valverde_ada.modelo.Ruta;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    private ArrayList<Ruta> rutas=new ArrayList<>();
    GalleryViewModel galleryViewModel;
    EditText longInicial,latInicial,rumbo,distancia;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Así se instancia un viewModel
        galleryViewModel =new ViewModelProvider(this).get(GalleryViewModel.class);


        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        longInicial=binding.longInicial;
        latInicial=binding.latInicial;
        rumbo=binding.rumbo;
        distancia=binding.distancia;
        //galleryViewModel.getText().observe(this.getViewLifecycleOwner(), textView::setText);



        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                galleryViewModel.addRuta(Integer.parseInt(longInicial.getText().toString()),
                        Integer.parseInt(latInicial.getText().toString()),
                        Integer.parseInt(rumbo.getText().toString()),
                        Integer.parseInt(distancia.getText().toString()));


            }
        });

        binding.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rutas=galleryViewModel.getRutas().getValue();
                for(Ruta r:rutas){
                   Log.d("MVVM", r.toString());
                }
            }
        });

        return root;


    }
    @Override
    public void onResume() {
        super.onResume();
        galleryViewModel.getRutas().observe(this.getViewLifecycleOwner(), new Observer<ArrayList<Ruta>>() {
            @Override
            public void onChanged(ArrayList<Ruta> rutas) {
                // Aquí puedes actualizar la interfaz de usuario con los nuevos datos
                Log.d("MVVM", ""+ rutas.toString());

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}