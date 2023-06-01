package com.example.minichef_v1.bd.dao.usuario

import android.view.View
import com.example.minichef_v1.bd.modelo.Usuario
import com.example.minichef_v1.pantanllas.home.detallePublicacion.DetallePublicacionViewModel

interface IDAOUsuario {
    fun crearUsuarioNoAdmin(usuario: Usuario)
    fun comprobarUsuarioExiste(id:String,v:View)
    fun getUsuarioByIdFromLogin(id:String,v: View)
    fun getUsuarioByIdFromDetallePublicion(id:String,holder:DetallePublicacionViewModel)

    fun anadeNuevaPublicacion(id:String)

    fun anadeNuevaSeguidor(id:String)

    fun anadeNuevaSiguiendo(id:String)


    fun quitaNuevaPublicacion(id:String)

    fun quitaNuevaSeguidor(id:String)

    fun quitaNuevaSiguiendo(id:String)

    fun banearUsuario(id: String)

    fun unbanearUsuario(id: String)
}