package com.example.kotllin_prueba_sqlite.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotllin_prueba_sqlite.room.dao.IDAOArtista
import com.example.kotllin_prueba_sqlite.room.dao.IDAOCancion
import com.example.kotllin_prueba_sqlite.room.entities.Artista
import com.example.kotllin_prueba_sqlite.room.entities.Cancion


@Database(entities = [Artista::class,Cancion::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun daoArtista(): IDAOArtista
    abstract fun daoCancion(): IDAOCancion
    fun destroyInstance() {
        INSTANCE = null
    }

    companion object {
        var INSTANCE: AppDB? = null

        fun getAppDB(context: Context): AppDB? {
            if (INSTANCE == null) {
                //Usar allowMainThreadQueries() solo para pruebas
                INSTANCE = Room
                    .databaseBuilder(
                        context.applicationContext,
                        AppDB::class.java,
                    "Spotify"
                    ).allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}