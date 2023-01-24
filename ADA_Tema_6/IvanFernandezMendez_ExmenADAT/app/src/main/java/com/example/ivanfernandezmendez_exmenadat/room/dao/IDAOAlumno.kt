package com.example.ivanfernandezmendez_exmenadat.room.dao

import androidx.room.*
import com.example.ivanfernandezmendez_exmenadat.room.entidades.Alumno
import com.example.ivanfernandezmendez_exmenadat.room.entidades.Asignatura

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

    @Query("SELECT * FROM alumno WHERE id_grupo LIKE :grupo")
    fun verAlumnosGrupo(grupo: String?): List<Alumno>

    @Query("SELECT alumno.* FROM alumno,alumnoestaasignatura WHERE alumno.id_alumno=alumnoestaasignatura.id_alumno AND alumnoestaasignatura.id_asignatura LIKE :asignatura")
    fun verAlumnosAsignatura(asignatura: Int): List<Alumno>

    @Query("SELECT * FROM alumno")
    fun verAlumnos(): List<Alumno>
}