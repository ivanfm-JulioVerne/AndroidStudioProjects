package com.example.prueba_sqliteroom.room.iDaos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.prueba_sqliteroom.room.entidades.Artista;

import java.util.List;

@Dao
public interface Artista_IDao {
    @Insert
    public void crearArtista(Artista artista);
    @Delete
    public void borrarArtista(Artista artista);
    @Update
    public void modificarArtista(Artista artista);
    @Query("SELECT * FROM artista WHERE id_artista LIKE :id")
    public Artista verArtista(int id);
    @Query("SELECT * FROM artista WHERE nombre LIKE :nombre")
    public Artista verArtista(String nombre);
    @Query("SELECT * FROM artista")
    public List<Artista> verArtistas();
}
