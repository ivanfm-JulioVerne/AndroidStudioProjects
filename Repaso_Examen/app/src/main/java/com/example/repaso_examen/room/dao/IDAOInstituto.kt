package com.example.repaso_examen.room.dao

import androidx.room.*
import com.example.repaso_examen.room.entidades.Instituto

@Dao
interface IDAOInstituto {
    @Insert
    fun crearInstituto(instituto: Instituto)

    @Delete
    fun borrarInstituto(instituto: Instituto)

    @Update
    fun modificarInstituto(instituto: Instituto)

    @Query("SELECT * FROM instituto WHERE id_instituto LIKE :id")
    fun verInstituto(id: Int): Instituto

    @Query("SELECT * FROM instituto WHERE nombre LIKE :nombre")
    fun verInstituto(nombre: String?): Instituto

    @Query("SELECT * FROM instituto")
    fun verInstitutos(): List<Instituto>
}