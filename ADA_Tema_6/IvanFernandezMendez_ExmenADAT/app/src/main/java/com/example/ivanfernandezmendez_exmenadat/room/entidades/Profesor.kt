package com.example.ivanfernandezmendez_exmenadat.room.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Profesor")
data class Profesor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_profesor")
    var id_profesor:Int?,
    @ColumnInfo(name = "nombre")
    var nombre:String,
    @ColumnInfo(name = "apellido")
    var apellido:String
){

constructor(nombre: String,apellido: String):
this(id_profesor=null,
nombre = nombre,
apellido=apellido){}}