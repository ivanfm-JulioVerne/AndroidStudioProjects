package com.example.pruebafragments2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ModoNoche.GestorEventosFragmentNoche {
    boolean modo_dia=false;
    static int numero=0;
    Fragment mn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cargar un fragment en un Activity mediante código
        mn=new ModoNoche();
        loadFragment(mn,"noche");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment f = null;
                if (modo_dia){
                    replaceFragment(mn,"noche");
                }
                else {
                    mn= (ModoNoche)getSupportFragmentManager().findFragmentByTag("noche");
                    loadFragment(new ModoDia(),"dia");
                }
                modo_dia=!modo_dia;


            }
        });

    }

    //Diferentes acciones a realizar con los Fragments

    private void loadFragment(Fragment f,String tag){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contenedor, f,tag)
                .commit();
    }
    private void replaceFragment(Fragment f,String tag){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, f,tag)
                .commit();
    }
    private void replaceAddToBack(Fragment f){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, f)
                .addToBackStack(null)
                .commit();
    }
    private void removeFragment(Fragment f){
        getSupportFragmentManager()
                .beginTransaction()
                .remove(f)
                .commit();
    }


    //Podemos gestionar un evento de click de botón a través de vinculación con XML
    public void pulsado(View view) {

        //se puede poner el evento en el fragment??
        //como hacer referencia al fragment y los widgets que en él hay??
        //Como saber si el fragment está activo??


        Button b= view.findViewById(R.id.button);

        b.setText("adiós");



    }

    //Mediante la técnica de Interfaz propio capturamos otros eventos de un fragment
    @Override
    public void onClickButtonModoNoche() {

            ModoNoche mn= (ModoNoche)getSupportFragmentManager().findFragmentByTag("noche");

            TextView tv=mn.getActivity().findViewById(R.id.textViewNoche);
            numero++;
            tv.setText(numero+"");


    }

}
