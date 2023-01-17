package com.example.ivan_fernandez_mendez_examen2.ui.GestionDietas;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ivan_fernandez_mendez_examen2.R;

public class CrearDieta extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crear_dieta, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button b_cambia=getView().findViewById(R.id.b_cam_crear);

        b_cambia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Metodo que cambia de fragment (hay que importarlo)
                findNavController(view).navigate(R.id.action_crearDieta_to_verDietas);
            }
        });

    }
}