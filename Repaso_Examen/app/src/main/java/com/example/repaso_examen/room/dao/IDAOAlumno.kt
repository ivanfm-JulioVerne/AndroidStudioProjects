package com.example.repaso_examen.room.dao

import androidx.room.*
import com.example.repaso_examen.room.entidades.Alumno
import com.example.repaso_examen.room.entidades.Instituto

@Dao
interface IDAOAlumno {
    @Insert
    fun crearAlumno(alumno: Alumno)

    @Delete
    fun borrarAlumno(alumno: Alumno)

    @Update
    fun modificarAlumno(alumno: Alumno)

    @Query("SELECT * FROM alumno WHERE id_alumno LIKE :id")
    fun verAlumno(id: Int): Alumno

    @Query("SELECT * FROM alumno WHERE nombre LIKE :nombre")
    fun verAlumno(nombre: String?): Alumno

    @Query("SELECT * FROM alumno")
    fun verAlumnos(): List<Alumno>
}