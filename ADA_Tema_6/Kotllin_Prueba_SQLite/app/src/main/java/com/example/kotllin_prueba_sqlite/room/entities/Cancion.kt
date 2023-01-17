package com.example.kotllin_prueba_sqlite.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName="cancion", foreignKeys = [
    ForeignKey(entity = Artista::class,
        parentColumns = ["id_artista"],
        childColumns = ["id_artista"],
        onDelete = CASCADE)
] )

class Cancion(
    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="id_cancion")
    var id_cancion:Int?,
    @ColumnInfo(name="nombre")
    var nombre:String,
    @ColumnInfo(name="anyo")
    var anyo:Int,
    @ColumnInfo (name = "id_artista")
    var id_artista:Int
) {
    constructor(nombre: String,anyo: Int,id_artista: Int):
            this(id_cancion = null,
                nombre = nombre,
                anyo = anyo,
                id_artista = id_artista){

    }
}