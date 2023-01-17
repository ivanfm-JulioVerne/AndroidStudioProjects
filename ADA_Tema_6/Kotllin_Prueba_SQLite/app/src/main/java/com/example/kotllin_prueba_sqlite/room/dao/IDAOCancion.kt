package com.example.kotllin_prueba_sqlite.room.dao

import androidx.room.*
import com.example.kotllin_prueba_sqlite.room.entities.Artista
import com.example.kotllin_prueba_sqlite.room.entities.Cancion

@Dao
interface IDAOCancion {
    @Insert
    fun crearCancion(cancion: Cancion)

    @Delete
    fun borrarCancion(cancion: Cancion)

    @Update
    fun modificarCancion(cancion: Cancion)

    @Query("SELECT * FROM cancion WHERE id_cancion LIKE :id")
    fun verCancion(id: Int): Cancion

    @Query("SELECT * FROM cancion WHERE nombre LIKE :nombre")
    fun verCancion(nombre: String?): Cancion

    @Query("SELECT * FROM cancion")
    fun verCanciones(): List<Cancion>
}