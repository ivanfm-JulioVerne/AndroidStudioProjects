package com.example.kotlinbasedatosexamen.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinbasedatosexamen.room.dao.IDAOAlimento
import com.example.kotlinbasedatosexamen.room.entities.Alimento

@Database(entities = [Alimento::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun daoAlimento(): IDAOAlimento
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
                        "Recetas"
                    ).allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}