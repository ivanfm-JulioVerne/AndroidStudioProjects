package com.example.ivanfernandezmendez_exmenadat.room.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Alumno", foreignKeys = [
    ForeignKey(entity = Grupo::class,
        parentColumns = ["id_grupo"],
        childColumns = ["id_grupo"],
        onDelete = ForeignKey.CASCADE
    )])
data class Alumno(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_alumno")
    var id_alumno:Int?,
    @ColumnInfo(name = "nombre")
    var nombre:String,
    @ColumnInfo(name = "apellido")
    var apellido:String,
    @ColumnInfo(name="id_grupo")
    var id_grupo: String
){

constructor(nombre: String,apellido: String,id_grupo: String):
this(id_alumno=null,
nombre = nombre,
apellido=apellido,
id_grupo = id_grupo){}}