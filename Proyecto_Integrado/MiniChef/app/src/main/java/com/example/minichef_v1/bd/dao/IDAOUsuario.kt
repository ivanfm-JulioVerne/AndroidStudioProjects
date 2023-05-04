package com.example.minichef_v1.bd.dao

import android.view.View
import com.example.minichef_v1.bd.modelo.Usuario

interface IDAOUsuario {
    fun crearUsuarioNoAdmin(usuario: Usuario)
    fun comprobarUsuarioExiste(id:String,v:View)
    fun getUsuarioByIdFromLogin(id:String,v: View)
}