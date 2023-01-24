package com.example.ivanfernandezmendez_exmenadat.room.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "AlumnoEstaAsignatura", foreignKeys = [
    ForeignKey(entity = Alumno::class,
        parentColumns = ["id_alumno"],
        childColumns = ["id_alumno"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(entity = Asignatura::class,
        parentColumns = ["id_asignatura"],
        childColumns = ["id_asignatura"],
        onDelete = ForeignKey.CASCADE
    )], primaryKeys = ["id_alumno","id_asignatura"])
data class AlumnoEstaAsignatura(
    @ColumnInfo(name = "id_alumno")
    var id_alumno:Int,
    @ColumnInfo(name = "id_asignatura")
    var id_asigantura:Int
){}