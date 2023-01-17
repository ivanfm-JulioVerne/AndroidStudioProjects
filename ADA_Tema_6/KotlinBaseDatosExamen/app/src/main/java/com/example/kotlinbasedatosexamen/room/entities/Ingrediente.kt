package com.example.kotlinbasedatosexamen.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "receta", foreignKeys = [
    ForeignKey(entity = Receta::class,
        parentColumns = ["id_receta"],
        childColumns = ["id_receta"],
        onDelete = CASCADE),
    ForeignKey(entity = Alimento::class,
        parentColumns = ["id_alimento"],
        childColumns = ["id_alimento"],
        onDelete = CASCADE)])
data class Ingrediente (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_receta")
    var id_ingrediente:Int?,
    @ColumnInfo(name = "cantidad")
    var cantidad:Int,
    @ColumnInfo(name = "id_receta")
    var id_receta: Int?,
    @ColumnInfo(name = "id_alimento")
    var id_alimento:Int?
){
    constructor(cantidad: Int,id_receta: Int?,id_alimento: Int?):
            this(
                id_ingrediente=null,
                cantidad=cantidad,
                id_receta=id_receta,
                id_alimento=id_alimento)
}