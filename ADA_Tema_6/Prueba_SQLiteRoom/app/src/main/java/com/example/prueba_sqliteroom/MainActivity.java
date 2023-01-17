package com.example.prueba_sqliteroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.prueba_sqliteroom.room.AppDB;
import com.example.prueba_sqliteroom.room.entidades.Artista;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Artista newArtista=new Artista("Joaquin Sabina","ESP");
        //AppDB.getAppDB(getApplicationContext()).daoArtista().crearArtista(newArtista);

        Log.d("Spotify_Artista","-----------------------INICIANDO LECTURA------------------");
        List<Artista> artistas=AppDB
                .getAppDB(getApplicationContext())
                .daoArtista()
                .verArtistas();

        for (Artista artista:artistas) {
            Log.d("Spotify_Artista", artista.getNombre());
        }

    }
}