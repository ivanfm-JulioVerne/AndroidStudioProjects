package com.example.guardar_usuario_ivan_fernandez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        Bundle extras=this.getIntent().getExtras();

        ArrayList<Usuario> listaUsuarios =(ArrayList<Usuario>)extras.getSerializable(
                "listaUsuarios");

        for (int inx = 0; inx < listaUsuarios.size(); inx++) {
            System.out.println(listaUsuarios.get(inx).mostrar());
    }
    }
}