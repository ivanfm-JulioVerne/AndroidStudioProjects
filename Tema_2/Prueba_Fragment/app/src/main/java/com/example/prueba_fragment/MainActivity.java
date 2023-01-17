package com.example.prueba_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    Fragment md;
    boolean modo_dia=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        md=new ModoDia();
        loadFragment(md,"dia");

        /*Button fab = findViewById(R.id.b_dia);
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