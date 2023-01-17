package com.example.kotllin_prueba_sqlite.room.dao

import androidx.room.*
import com.example.kotllin_prueba_sqlite.room.entities.Artista


@Dao
interface IDAOArtista {

    @Insert
    fun crearArtista(artista: Artista)

    @Delete
    fun borrarArtista(artista: Artista)

    @Update
    fun modificarArtista(artista: Artista)

    @Query("SELECT * FROM artista WHERE id_artista LIKE :id")
    fun verArtista(id: Int): Artista

    @Query("SELECT * FROM artista WHERE nombre LIKE :nombre")
    fun verArtista(nombre: String?): Artista

    @Query("SELECT * FROM artista")
    fun verArtistas(): List<Artista>
}