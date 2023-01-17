package com.example.pruebafragments2;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModoNoche extends Fragment {

    public GestorEventosFragmentNoche gefn;

    public ModoNoche() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_modo_noche, container, false);
    }

    // Para acceder a widgets se empela getView().findViewById(..) ya que
    // El fragment no tiene el método findViewById()
    // Se hace en el método onActivityCreated para asegurarnos de que el Activity ya
    // está operativo con los fragments cargados.

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        Button b2 = getView().findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b2 = getView().findViewById(R.id.button2);
                b2.setText("hola noche");

            }
        });

        Button b3 = getView().findViewById(R.id.button3);


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gefn.onClickButtonModoNoche();

            }
        });

    }

    //Una técnica para ejecutar eventos de widgets de fragments dentro del Activ¡ty es
    //por medio de una interfaz
    //La creamos aquí mísmo

    public interface GestorEventosFragmentNoche{
        public void onClickButtonModoNoche();



    }


    //Se usa en el método onAttach que se lanza cuando el fragment se asocia con el Activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //En este bloque nos aseguramos de que el activity tiene implementado el interfaz
        try {
            gefn = (GestorEventosFragmentNoche)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " debes implementar GestorEventosModoNoche");
        }

    }


}
