package com.example.ivanfernandezmendez_exmenadat.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ivanfernandezmendez_exmenadat.room.dao.*
import com.example.ivanfernandezmendez_exmenadat.room.entidades.*

@Database(entities = [Alumno::class,Grupo::class,Asignatura::class, Profesor::class, AlumnoEstaAsignatura::class,Departamento::class], version = 2)
abstract class AppDB : RoomDatabase() {
    abstract fun daoAlumno(): IDAOAlumno
    abstract fun daoGrupo(): IDAOGrupo
    abstract fun daoAsignatura(): IDAOAsignatura
    abstract fun daoProfesor(): IDAOProfesor
    abstract fun daoAlumnoEstaAsignatura(): IDAOAlumnoEstaAsignatura
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
                        "Instituto"
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
                            "('id_departamento' TEXT NOT NULL,'nombre' TEXT NOT NULL,'id_profesor' INTEGER NOT NULL," +
                            " PRIMARY KEY('id_departamento'), FOREIGN KEY (id_profesor) REFERENCES Profesor(id_profesor))"
                )
            }
        }
    }
}