package com.example.kotllin_prueba_sqlite

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotllin_prueba_sqlite.room.AppDB
import com.example.kotllin_prueba_sqlite.room.entities.Artista
import com.example.kotllin_prueba_sqlite.room.entities.Cancion


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var artista = Artista(nombre="Lil Nas X", nacionalidad ="EEUU")
        AppDB.getAppDB(getApplicationContext())!!.daoArtista().crearArtista(artista)

        var cancion= Cancion(nombre = "Holyday", anyo = 2020, id_artista = 1)
        AppDB.getAppDB(getApplicationContext())!!.daoCancion().crearCancion(cancion)

        val artistas: List<Artista>? =
            AppDB.getAppDB(applicationContext)!!.daoArtista().verArtistas()
        Log.d(":::Spotify", "Artistas")
        if (artistas != null) {
            for (ar in artistas) {
                Log.d(":::Spotify","Id artista= ${ar.id_artista}")
            }
        }

        val canciones: List<Cancion>? =
            AppDB.getAppDB(applicationContext)!!.daoCancion().verCanciones()
        Log.d(":::Spotify", "Canciones")
        if (artistas != null) {
            for (ar in artistas) {
                Log.d(":::Spotify",ar.nombre)
            }
        }
    }
}