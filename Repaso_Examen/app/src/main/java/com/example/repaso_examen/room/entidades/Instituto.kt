package com.example.repaso_examen.room.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Instituto")
data class Instituto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_instituto")
    var id_instituto:Int?,
    @ColumnInfo(name = "nombre")
    var nombre:String,
    @ColumnInfo(name = "direccion")
    var direccion:String
){
    constructor(nombre: String,direccion: String):
            this(id_instituto=null,
                nombre = nombre,
                direccion = direccion){}
}
