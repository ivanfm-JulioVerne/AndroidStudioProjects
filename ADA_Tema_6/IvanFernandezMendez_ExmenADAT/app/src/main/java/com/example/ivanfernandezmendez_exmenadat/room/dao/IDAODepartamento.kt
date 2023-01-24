package com.example.ivanfernandezmendez_exmenadat.room.dao

import androidx.room.*
import com.example.ivanfernandezmendez_exmenadat.room.entidades.Alumno
import com.example.ivanfernandezmendez_exmenadat.room.entidades.Departamento
import com.example.ivanfernandezmendez_exmenadat.room.entidades.Profesor

@Dao
interface IDAODepartamento {
    @Insert
    fun crearDepartamento(departamento: Departamento)

    @Delete
    fun borrarDepartamento(departamento: Departamento)

    @Update
    fun modificarDepartamento(departamento: Departamento)

    @Query("SELECT * FROM Departamento WHERE id_departamento LIKE :id")
    fun verDepartamento(id: String): Departamento

    @Query("SELECT * FROM Departamento")
    fun verDepartamentos(): List<Departamento>
}