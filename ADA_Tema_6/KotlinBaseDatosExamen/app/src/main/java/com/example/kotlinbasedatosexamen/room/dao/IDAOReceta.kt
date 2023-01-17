package com.example.kotlinbasedatosexamen.room.dao

import androidx.room.*
import com.example.kotlinbasedatosexamen.room.entities.Alimento
import com.example.kotlinbasedatosexamen.room.entities.Receta

@Dao
interface IDAOReceta {

    @Insert
    fun crearReceta(receta: Receta)

    @Delete
    fun borrarReceta(receta: Receta)

    @Update
    fun modificarReceta(receta: Receta)

    @Query("SELECT * FROM receta WHERE id_receta LIKE :id")
    fun verReceta(id: Int): Receta

    @Query("SELECT * FROM receta WHERE nombre LIKE :nombre")
    fun verReceta(nombre: String?): Receta

    @Query("SELECT * FROM receta")
    fun verRecetas(): List<Receta>
}