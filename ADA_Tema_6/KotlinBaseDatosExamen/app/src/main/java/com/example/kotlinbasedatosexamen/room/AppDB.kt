package com.example.kotlinbasedatosexamen.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kotlinbasedatosexamen.room.dao.IDAOAlimento
import com.example.kotlinbasedatosexamen.room.dao.IDAOReceta
import com.example.kotlinbasedatosexamen.room.entities.Alimento
import com.example.kotlinbasedatosexamen.room.entities.Receta

@Database(entities = [Alimento::class,Receta::class], version = 2)
abstract class AppDB : RoomDatabase() {
    abstract fun daoAlimento(): IDAOAlimento
    abstract fun daoReceta(): IDAOReceta
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
                    .addMigrations(MIGRATION_1_2)
                    .build()
            }
            return INSTANCE
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE 'receta' " +
                            "('id_receta' INTEGER,'nombre' TEXT NOT NULL," +
                            " PRIMARY KEY('id_receta'))"
                )
            }
        }
    }
}