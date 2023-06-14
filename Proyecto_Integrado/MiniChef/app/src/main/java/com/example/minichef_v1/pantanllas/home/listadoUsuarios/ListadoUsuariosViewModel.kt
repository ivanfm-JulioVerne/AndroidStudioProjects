package com.example.minichef_v1.pantanllas.home.listadoUsuarios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minichef_v1.bd.dao.usuario.DAOUsuario
import com.example.minichef_v1.bd.modelo.Usuario

class ListadoUsuariosViewModel(val admin: Boolean):ViewModel() {

    private val daoUsuario=DAOUsuario()

    private val _lista = MutableLiveData<List<Usuario>>().apply {
        value = emptyList()
    }
    val lista: LiveData<List<Usuario>> = _lista

    init {
        buscarUsuariosPopulares()
    }

    fun buscarUsuariosPopulares(){
        daoUsuario.getUsuariosPopulares(this,admin)
    }

    fun buscarUsuarioPorNickname(nickname: String){
        daoUsuario.getUsuarioByNickname(nickname,this,admin)
    }

    fun setLista(usuarios:List<Usuario>){
        _lista.value=usuarios
    }
}

@Suppress("UNCHECKED_CAST")
class ListadoUsuariosViewModelFactory(private val admin:Boolean): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListadoUsuariosViewModel(admin) as T
    }
}