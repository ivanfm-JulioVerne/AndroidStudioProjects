package com.example.kotllin_prueba_sqlite.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="Artista")
data class Artista (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_artista")
    var id_artista:Int?,
    @ColumnInfo(name = "nombre")
    var nombre: String,
    @ColumnInfo(name = "nacionalidad")
    var nacionalidad:String


){
    constructor(nombre: String,nacionalidad: String) : this(null,nombre, nacionalidad) {

    }
}