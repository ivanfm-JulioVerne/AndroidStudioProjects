package com.example.minichef_v1.pantanllas.perfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minichef_v1.bd.dao.publicacion.DAOPublicacion
import com.example.minichef_v1.bd.dao.usuario.DAOUsuario
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.bd.modelo.Usuario

class PerfilViewModel(val usuario: Usuario) : ViewModel() {

    private val daoPublicacion= DAOPublicacion()

    private val daoUsuario=DAOUsuario()

    private val _lista = MutableLiveData<List<Publicacion>>().apply {
        value = emptyList()
    }
    val lista: LiveData<List<Publicacion>> = _lista

    init {
        getPublicacionesPorUsuarios()
    }

    fun getPublicacionesPorUsuarios(){
        daoPublicacion.getPublicacionesPorUsuario(this,usuario.id_usuario)
    }

    fun eliminarUsuario(){
        daoUsuario.eliminarUsuario(usuario.id_usuario)
    }

    fun setLista(publicaciones:List<Publicacion>){
        _lista.value=publicaciones
    }
}

@Suppress("UNCHECKED_CAST")
class PerfilViewModelFactory(private val usuario:Usuario):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PerfilViewModel(usuario) as T
    }
}