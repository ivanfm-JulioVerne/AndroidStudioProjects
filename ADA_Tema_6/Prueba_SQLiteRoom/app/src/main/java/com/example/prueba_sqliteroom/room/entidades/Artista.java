package com.example.prueba_sqliteroom.room.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.prueba_sqliteroom.room.Constantes;

@Entity(tableName= Constantes.Constante.NOMBRE_TABLA_ARTISTA)
public class Artista {
    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="id_artista")
    private int id_alimento;
    @ColumnInfo(name="nombre")
    private String nombre;
    @ColumnInfo(name="nacionalidad")
    private String nacionalidad;

    public Artista(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public int getId_alimento() {
        return id_alimento;
    }

    public void setId_alimento(int id_alimento) {
        this.id_alimento = id_alimento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
