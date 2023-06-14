package com.example.minichef_v1.bd.modelo

data class Comentario(
    val idComentario:String,
    val idPublicacion:String,
    val idUsuario:String,
    val texto:String,
    var baneado:Boolean,
    val nickname:String
)
