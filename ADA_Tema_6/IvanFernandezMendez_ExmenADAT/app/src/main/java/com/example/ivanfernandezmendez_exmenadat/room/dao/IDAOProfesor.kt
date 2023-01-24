package com.example.ivanfernandezmendez_exmenadat.room.dao

import androidx.room.*
import com.example.ivanfernandezmendez_exmenadat.room.entidades.Alumno
import com.example.ivanfernandezmendez_exmenadat.room.entidades.Profesor

@Dao
interface IDAOProfesor {
    @Insert
    fun crearProfesor(profesor: Profesor)

    @Delete
    fun borrarProfesor(profesor: Profesor)

    @Update
    fun modificarProfesor(profesor: Profesor)

    @Query("SELECT * FROM Profesor WHERE id_profesor LIKE :id")
    fun verProfesor(id: Int): Profesor

    @Query("SELECT * FROM Profesor WHERE nombre LIKE :nombre")
    fun verProfesor(nombre: String?): Profesor

    @Query("SELECT * FROM Profesor")
    fun verProfesores(): List<Profesor>
}