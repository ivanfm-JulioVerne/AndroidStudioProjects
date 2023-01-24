package com.example.ivanfernandezmendez_exmenadat.room.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Asignatura")
data class Asignatura(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_asignatura")
    var id_asignatura:Int?,
    @ColumnInfo(name = "nombre")
    var nombre:String
){

constructor(nombre: String):
this(id_asignatura=null,
nombre = nombre){}}