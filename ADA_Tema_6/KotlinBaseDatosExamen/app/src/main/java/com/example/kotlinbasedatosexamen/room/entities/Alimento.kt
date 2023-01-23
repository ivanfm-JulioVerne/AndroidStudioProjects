package com.example.kotlinbasedatosexamen.room.entities

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Alimento")
data class Alimento(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_alimento")
    var id_alimento:Int?,
    @ColumnInfo(name = "nombre")
    var nombre:String,
    @ColumnInfo(name = "proteinas")
    var proteinas:Int,
    @ColumnInfo(name = "grasas")
    var grasas:Int,
    @ColumnInfo(name = "hidratos")
    var hidratos:Int

){
    constructor(nombre: String,proteinas: Int,grasas: Int,hidratos: Int):
            this(id_alimento=null,
                nombre = nombre,
                proteinas = proteinas,
                grasas = grasas,
                hidratos = hidratos){}

    fun mostrar():String{
        return("Alimento:\n" +
                " - Id: ${this.id_alimento}\n" +
                " - Nombre: ${this.nombre}\n" +
                " - Proteinas: ${this.proteinas}\n" +
                " - Grasas: ${this.grasas}\n" +
                " - Hidratos: ${this.hidratos}")
    }
}
