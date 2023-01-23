package com.example.repaso_examen.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.repaso_examen.room.dao.IDAOAlumno
import com.example.repaso_examen.room.dao.IDAODepartamento
import com.example.repaso_examen.room.dao.IDAOInstituto
import com.example.repaso_examen.room.entidades.Alumno
import com.example.repaso_examen.room.entidades.Departamento
import com.example.repaso_examen.room.entidades.Instituto

@Database(entities = [Instituto::class,Alumno::class,Departamento::class], version = 2)
abstract class AppDB : RoomDatabase() {
    abstract fun daoInstituto():IDAOInstituto
    abstract fun daoAlumno():IDAOAlumno
    abstract fun daoDepartamento():IDAODepartamento
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
                        "Institutos"
                    ).allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .build()
            }
            return INSTANCE
        }
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE 'Departamento' " +
                            "('id_departamento' INTEGER,'nombre' TEXT NOT NULL,'id_instituto' INTEGER," +
                            " PRIMARY KEY('id_departamento')," +
                            "FOREIGN KEY (id_instituto) REFERENCES Instituto(id_instituto) ON DELETE CASCADE)"
                )
            }
        }
    }
}