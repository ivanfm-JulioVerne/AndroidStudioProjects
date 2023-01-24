package com.example.ivanfernandezmendez_exmenadat.room.dao

import androidx.room.*
import com.example.ivanfernandezmendez_exmenadat.room.entidades.Alumno
import com.example.ivanfernandezmendez_exmenadat.room.entidades.AlumnoEstaAsignatura

@Dao
interface IDAOAlumnoEstaAsignatura {
    @Insert
    fun crearAlumnoEstaAsignatura(alumnoEstaAsignatura: AlumnoEstaAsignatura)

    @Delete
    fun borrarAlumnoEstaAsignatura(alumnoEstaAsignatura: AlumnoEstaAsignatura)

    @Update
    fun modificarAlumnoEstaAsignatura(alumnoEstaAsignatura: AlumnoEstaAsignatura)

    @Query("SELECT * FROM AlumnoEstaAsignatura WHERE id_alumno LIKE :id")
    fun verAlumnoEstaAsignatura(id: Int): AlumnoEstaAsignatura

    @Query("SELECT * FROM AlumnoEstaAsignatura")
    fun verAlumnoEstaAsignaturas(): List<AlumnoEstaAsignatura>
}