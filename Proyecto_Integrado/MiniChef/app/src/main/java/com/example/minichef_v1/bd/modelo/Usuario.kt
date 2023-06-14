package com.example.minichef_v1.bd.modelo

data class Usuario (
    var id_usuario:String,
    var nickname:String,
    var nombre:String,
    var biografia:String?,
    var admin:Boolean,
    var baneado:Boolean,
    var num_seguidores:Long,
    var num_siguiendo:Long,
    var num_publicacion:Long,
        ) {
    constructor(id_usuario: String, nickname: String, nombre: String, biografia: String) :
            this(
                id_usuario = id_usuario,
                nickname = nickname,
                nombre = nombre,
                biografia = biografia,
                admin = false,
                baneado = false,
                num_seguidores = 0,
                num_siguiendo = 0,
                num_publicacion = 0
            )

    constructor() : this(
        "","","",""
    )
    /*constructor(id_usuario: String,admin: Boolean, nickname: String, nombre: String,
                biografia: String,num_seguidores: Int,num_siguiendo: Int,num_publicacion: Int) :
            this(
                id_usuario = id_usuario,
                nickname = nickname,
                nombre = nombre,
                biografia = biografia,
                admin = admin,
                num_seguidores = num_seguidores,
                num_siguiendo = num_siguiendo,
                num_publicacion = num_publicacion
            ) {
    }*/
}