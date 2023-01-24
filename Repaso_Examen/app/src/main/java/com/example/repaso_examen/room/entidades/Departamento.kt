package com.example.repaso_examen.room.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Departamento", foreignKeys = [
    ForeignKey(entity = Instituto::class,
        parentColumns = ["id_instituto"],
        childColumns = ["id_instituto"],
        onDelete = ForeignKey.CASCADE
    )])
data class Departamento(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_departamento")
    var id_departamento:Int?,
    @ColumnInfo(name = "nombre")
    var nombre:String,
    @ColumnInfo(name = "id_instituto")
    var id_instituto:Int?,
){
    constructor(nombre: String,id_instituto:Int):
            this(id_departamento=null,
                nombre = nombre,
                id_instituto = id_instituto){}
}
