package com.example.repaso_examen.room.dao

import androidx.room.*
import com.example.repaso_examen.room.entidades.Departamento
import com.example.repaso_examen.room.entidades.Instituto

@Dao
interface IDAODepartamento {
    @Insert
    fun crearDepartamento(departamento: Departamento)

    @Delete
    fun borrarDepartamento(departamento: Departamento)

    @Update
    fun modificarDepartamento(departamento: Departamento)

    @Query("SELECT * FROM departamento WHERE id_departamento LIKE :id")
    fun verDepartamento(id: Int): Departamento

    @Query("SELECT * FROM departamento WHERE nombre LIKE :nombre")
    fun verDepartamento(nombre: String?): Departamento

    @Query("SELECT * FROM departamento")
    fun verDepartamentos(): List<Departamento>
}