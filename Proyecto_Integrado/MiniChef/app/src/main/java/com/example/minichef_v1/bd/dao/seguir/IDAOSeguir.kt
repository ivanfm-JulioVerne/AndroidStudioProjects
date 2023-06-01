package com.example.minichef_v1.bd.dao.seguir

import com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.AutorViewModel

interface IDAOSeguir {

    fun seguir(idAutor:String,idUsuario:String)

    fun dejaSeguir(idAutor: String,idUsuario: String)

    fun leSigue(autorViewModel: AutorViewModel,idAutor:String,idUsuario:String)

}