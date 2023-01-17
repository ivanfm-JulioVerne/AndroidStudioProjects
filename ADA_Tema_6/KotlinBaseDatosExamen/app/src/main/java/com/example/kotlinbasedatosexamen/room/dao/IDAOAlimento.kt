package com.example.kotlinbasedatosexamen.room.dao

import androidx.room.*
import com.example.kotlinbasedatosexamen.room.entities.Alimento

@Dao
interface IDAOAlimento {

    @Insert
    fun crearAlimento(alimento: Alimento)

    @Delete
    fun borrarAlimento(alimento: Alimento)

    @Update
    fun modificarAlimento(alimento: Alimento)

    @Query("SELECT * FROM alimento WHERE id_alimento LIKE :id")
    fun verAlimento(id: Int): Alimento

    @Query("SELECT * FROM alimento WHERE nombre LIKE :nombre")
    fun verAlimento(nombre: String?): Alimento

    @Query("SELECT * FROM alimento")
    fun verAlimentos(): List<Alimento>
}