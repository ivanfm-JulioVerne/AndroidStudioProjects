package com.example.ivanfernandezmendez_exmenadat.room.dao

import androidx.room.*
import com.example.ivanfernandezmendez_exmenadat.room.entidades.Alumno
import com.example.ivanfernandezmendez_exmenadat.room.entidades.Asignatura
import com.example.ivanfernandezmendez_exmenadat.room.entidades.Grupo

@Dao
interface IDAOAsignatura {
    @Insert
    fun crearAsignatura(asignatura:Asignatura)

    @Delete
    fun borrarAsignatura(asignatura:Asignatura)

    @Update
    fun modificarAsignatura(asignatura:Asignatura)

    @Query("SELECT * FROM Asignatura WHERE id_asignatura LIKE :id")
    fun verAsignatura(id: Int): Asignatura

    @Query("SELECT * FROM Asignatura WHERE nombre LIKE :nombre")
    fun verAsignatura(nombre: String?): Asignatura

    @Query("SELECT * FROM Asignatura")
    fun verAsignaturas(): List<Asignatura>
}