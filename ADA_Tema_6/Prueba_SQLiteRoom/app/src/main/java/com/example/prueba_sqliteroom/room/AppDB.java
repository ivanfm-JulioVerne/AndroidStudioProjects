package com.example.prueba_sqliteroom.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.prueba_sqliteroom.room.entidades.Artista;
import com.example.prueba_sqliteroom.room.iDaos.Artista_IDao;

@Database(entities = {Artista.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public static AppDB INSTANCE;

    public abstract Artista_IDao daoArtista();

    public static AppDB getAppDB(Context context) {
        if (INSTANCE == null) {
            //Usar allowMainThreadQueries() solo para pruebas
            INSTANCE = Room
                    .databaseBuilder(context.getApplicationContext(), AppDB.class,
                            Constantes.Constante.NOMBRE_DB)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public void destroyInstance() {
        INSTANCE = null;
    }
}
