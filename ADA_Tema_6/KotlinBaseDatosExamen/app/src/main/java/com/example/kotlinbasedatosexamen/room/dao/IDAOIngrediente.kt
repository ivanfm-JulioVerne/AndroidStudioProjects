package com.example.kotlinbasedatosexamen.room.dao

import androidx.room.*
import com.example.kotlinbasedatosexamen.room.entities.Ingrediente
import com.example.kotlinbasedatosexamen.room.entities.Receta

@Dao
interface IDAOIngrediente {

    @Insert
    fun crearIngrediente(ingrediente: Ingrediente)

    @Delete
    fun borrarIngrediente(ingrediente: Ingrediente)
    @Update
    fun modificarIngrediente(ingrediente: Ingrediente)

    @Query("SELECT * FROM ingrediente WHERE id_ingrediente LIKE :id")
    fun verIngrediente(id: Int): Ingrediente

    @Query("SELECT * FROM ingrediente")
    fun verIngredientes(): List<Ingrediente>
}