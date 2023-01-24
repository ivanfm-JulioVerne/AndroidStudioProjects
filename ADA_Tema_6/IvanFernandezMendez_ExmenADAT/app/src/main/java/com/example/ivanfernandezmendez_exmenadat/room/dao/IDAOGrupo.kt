package com.example.ivanfernandezmendez_exmenadat.room.dao

import androidx.room.*
import com.example.ivanfernandezmendez_exmenadat.room.entidades.Alumno
import com.example.ivanfernandezmendez_exmenadat.room.entidades.Grupo

@Dao
interface IDAOGrupo {
    @Insert
    fun crearGrupo(grupo: Grupo)

    @Delete
    fun borrarGrupo(grupo: Grupo)

    @Update
    fun modificarGrupo(grupo: Grupo)

    @Query("SELECT * FROM Grupo WHERE id_grupo LIKE :id")
    fun verGrupo(id: String): Grupo

    @Query("SELECT * FROM Grupo")
    fun verGrupos(): List<Grupo>
}