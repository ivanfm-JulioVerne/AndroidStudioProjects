package com.example.repaso_examen.room.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Alumno")
data class Alumno(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_alumno")
    var id_alumno:Int?,
    @ColumnInfo(name = "dni")
    var dni:String,
    @ColumnInfo(name = "nombre")
    var nombre:String
){
    constructor(nombre: String,dni: String):
            this(id_alumno=null,
                nombre = nombre,
                dni = dni){}
}
