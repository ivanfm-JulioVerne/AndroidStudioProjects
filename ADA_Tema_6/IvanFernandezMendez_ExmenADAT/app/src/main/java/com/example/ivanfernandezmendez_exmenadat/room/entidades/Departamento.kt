package com.example.ivanfernandezmendez_exmenadat.room.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Departamento", foreignKeys = [
    ForeignKey(entity = Profesor::class,
        parentColumns = ["id_profesor"],
        childColumns = ["id_profesor"],
        onDelete = ForeignKey.CASCADE
    )])
data class Departamento(
    @PrimaryKey()
    @ColumnInfo(name = "id_departamento")
    var id_departamento:String,
    @ColumnInfo(name = "nombre")
    var nombre:String,
    @ColumnInfo(name = "id_profesor")
    var id_profesor:Int)