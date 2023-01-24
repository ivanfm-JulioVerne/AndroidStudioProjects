package com.example.kotlinbasedatosexamen.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receta")
data class Receta(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_receta")
    var id_receta:Int?,
    @ColumnInfo(name = "nombre")
    var nombre:String
){
    constructor(nombre: String):this(id_receta=null,nombre = nombre)

    fun mostrar():String{
        return("Receta:\n" +
                " - Id: ${this.id_receta}\n" +
                " - Nombre: ${this.nombre}")
    }
}
