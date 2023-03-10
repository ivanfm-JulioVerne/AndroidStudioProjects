package com.example.pruebasqlite2022_23;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.pruebasqlite2022_23.DaosSQLite.Dao;
import com.example.pruebasqlite2022_23.DaosSQLite.DaoDepartamentoSQL;
import com.example.pruebasqlite2022_23.DaosSQLite.GestionDepartamentosBD;
import com.example.pruebasqlite2022_23.pojos.Departamento;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dao.setGestorBD(new GestionDepartamentosBD(this));

        //fuerzo a crear de nuevo las tablas
      ((GestionDepartamentosBD)Dao.gestorBd).borrarTablas();
          Dao.gestorBd.onCreate(Dao.gestorBd.getWritableDatabase());


        DaoDepartamentoSQL daodep=new DaoDepartamentoSQL();



      daodep.crearDepartamento(new Departamento("Matemáticas"));
      daodep.crearDepartamento(new Departamento("Informática"));
      daodep.crearDepartamento(new Departamento("Biología y Geología"));



      Departamento depar= daodep.verDepartamento(1);


        ArrayList<Departamento> departamentos= daodep.verDepartamentos();


        for (Departamento dep:departamentos){

            Log.d("salida",dep.getNombre());
        }

        Log.d("salida",depar.getNombre());

        int x=1;

    }
}