package com.example.ivanfernandezmendez_exmenadat.room.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Grupo")
data class Grupo(
    @PrimaryKey()
    @ColumnInfo(name = "id_grupo")
    var id_grupo:String,
    @ColumnInfo(name = "nombre")
    var nombre:String,
    @ColumnInfo(name = "aula")
    var aula:String
){}