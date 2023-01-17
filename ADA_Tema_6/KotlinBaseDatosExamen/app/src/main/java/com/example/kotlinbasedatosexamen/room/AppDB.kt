package com.example.kotlinbasedatosexamen.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kotlinbasedatosexamen.room.dao.IDAOAlimento
import com.example.kotlinbasedatosexamen.room.dao.IDAOIngrediente
import com.example.kotlinbasedatosexamen.room.dao.IDAOReceta
import com.example.kotlinbasedatosexamen.room.entities.Alimento
import com.example.kotlinbasedatosexamen.room.entities.Ingrediente
import com.example.kotlinbasedatosexamen.room.entities.Receta

@Database(entities = [Alimento::class,Receta::class,Ingrediente::class], version = 3)
abstract class AppDB : RoomDatabase() {
    abstract fun daoAlimento(): IDAOAlimento
    abstract fun daoReceta(): IDAOReceta
    abstract fun daoIngrediente():IDAOIngrediente
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

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE 'ingrediente' " +
                            "('id_ingrediente' INTEGER,'cantidad' INTEGER NOT NULL," +
                            "'id_receta' INTEGER NOT NULL,'id_alimento' INTEGER NOT NULL," +
                            " PRIMARY KEY('id_ingrediente')," +
                            "FOREIGN KEY (id_receta) REFERENCES receta(id_receta)," +
                            "FOREIGN KEY (id_aliemnto) REFERENCES alimento(id_alimento))"
                )
            }
        }
    }
}