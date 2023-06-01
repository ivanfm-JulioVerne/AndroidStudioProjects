package com.example.minichef_v1.bd.dao.like

import com.example.minichef_v1.pantanllas.home.detallePublicacion.DetallePublicacionViewModel

interface IDAOLike {

    fun anadirLike(idPublicacion:String,idUsuario:String)

    fun quitarLike(idPublicacion:String,idUsuario:String)

    fun comprobarLike(
        detallePublicacionViewModel: DetallePublicacionViewModel,
        idPublicacion: String,
        idUsuario: String
    )

}