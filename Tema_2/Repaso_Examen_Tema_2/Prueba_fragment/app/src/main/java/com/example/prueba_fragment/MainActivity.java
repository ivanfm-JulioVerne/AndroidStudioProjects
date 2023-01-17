package com.example.prueba_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private Fragment md;
    private boolean modo_dia=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Cargar un fragment en un Activity mediante código
        md=new ModoDia();
        loadFragment(md,"dia");

        Button fab =findViewById(R.id.b_cambiar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (modo_dia){
                    Log.d("Aqui","He pasado por aqui dia");
                    md= (ModoDia)getSupportFragmentManager().findFragmentByTag("dia");
                    loadFragment(new ModoNoche(),"noche");
                    modo_dia=false;
                }
                else {
                    Log.d("Aqui","He pasado por aqui noche");
                    replaceFragment(md,"dia");
                    modo_dia=true;
                }
                //modo_dia=!modo_dia;

            }
        });
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment f = null;
                if (modo_dia){
                    md=null;
                    md=new ModoDia();
                    replaceFragment(md,"dia");
                }
                else {
                    md=null;
                    md=new ModoNoche();
                    replaceFragment(md,"noche");
                }
                modo_dia=!modo_dia;

            }
        });*/
    }

    //Diferentes acciones a realizar con los Fragments

    private void loadFragment(Fragment f, String tag){
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
}