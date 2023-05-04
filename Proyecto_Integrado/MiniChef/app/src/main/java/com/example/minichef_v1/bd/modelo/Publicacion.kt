package com.example.minichef_v1.bd.modelo

data class Publicacion(
    var titulo:String?,
    var descripcion:String?,
    var ingredientes:ArrayList<String>?,
    var pasos:ArrayList<String>?,
    var imagen:String?,
    var num_likes:Int?,
    var id_usuario:String?,
    var id_categoria:ArrayList<String>?
) {
    constructor() : this(
        titulo=null, descripcion=null, ingredientes=null, pasos=null, imagen=null, num_likes=0, id_usuario=null, id_categoria=null
    ) {

    }
}