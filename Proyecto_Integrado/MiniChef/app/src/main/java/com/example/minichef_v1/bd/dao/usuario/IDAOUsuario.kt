package com.example.minichef_v1.bd.dao.usuario

import android.view.View
import com.example.minichef_v1.AuthActivity
import com.example.minichef_v1.bd.modelo.Usuario
import com.example.minichef_v1.pantanllas.home.detallePublicacion.DetallePublicacionViewModel
import com.example.minichef_v1.pantanllas.home.listadoUsuarios.ListadoUsuariosViewModel

interface IDAOUsuario {
    fun crearUsuarioNoAdmin(usuario: Usuario)
    fun comprobarUsuarioExiste(id:String,v:View)

    fun getUsuarioLogueado(id: String,authActivity: AuthActivity)

    fun getUsuarioByIdFromLogin(id:String,v: View)

    fun getUsuarioByNickname(nickname:String,listadoUsuariosViewModel: ListadoUsuariosViewModel,admin: Boolean)

    fun getUsuariosPopulares(listadoUsuariosViewModel: ListadoUsuariosViewModel, admin: Boolean)
    fun getUsuarioByIdFromDetallePublicion(id:String,holder:DetallePublicacionViewModel)

    fun anadeNuevaPublicacion(id:String)

    fun anadeNuevaSeguidor(id:String)

    fun anadeNuevaSiguiendo(id:String)


    fun quitaNuevaPublicacion(id:String)

    fun quitaNuevaSeguidor(id:String)

    fun quitaNuevaSiguiendo(id:String)

    fun banearUsuario(id: String)

    fun unbanearUsuario(id: String)

    fun editarPerfil(usuario:Usuario)

    fun eliminarUsuario(idUsuario: String)

}