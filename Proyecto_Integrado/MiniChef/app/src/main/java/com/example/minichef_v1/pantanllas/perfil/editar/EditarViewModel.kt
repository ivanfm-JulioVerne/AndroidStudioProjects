package com.example.minichef_v1.pantanllas.perfil.editar

import com.example.minichef_v1.bd.dao.usuario.DAOUsuario
import com.example.minichef_v1.bd.modelo.Usuario

class EditarViewModel {

    private val daoUsuario: DAOUsuario = DAOUsuario()
    fun guardarUsuario(usuario: Usuario){
        daoUsuario.editarPerfil(usuario)
    }

}