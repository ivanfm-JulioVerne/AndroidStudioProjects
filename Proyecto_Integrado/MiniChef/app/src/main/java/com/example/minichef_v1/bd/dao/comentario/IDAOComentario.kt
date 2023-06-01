package com.example.minichef_v1.bd.dao.comentario

import com.example.minichef_v1.pantanllas.home.detallePublicacion.DetallePublicacionViewModel

interface IDAOComentario {

    fun borrarComentario(id:String)

    fun mostrarComentariosByPublicacion(
        detallePublicacionViewModel: DetallePublicacionViewModel,
        idPublicacion: String,admin:Boolean
    )

    fun anadirComentario(idPublicacion: String, idUsuario: String, texto: String, nickname: String)

    fun banComentarioPorUsuario(idUsuario:String)

    fun banComentario(id:String)

    fun unbanComentarioPorUsuario(idUsuario:String)

    fun unbanComentario(id:String)
}