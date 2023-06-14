package com.example.minichef_v1.pantanllas.home.detallePublicacion.autor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minichef_v1.bd.dao.publicacion.DAOPublicacion
import com.example.minichef_v1.bd.dao.publicacion.IDAOPublicacion
import com.example.minichef_v1.bd.dao.seguir.DAOSeguir
import com.example.minichef_v1.bd.dao.seguir.IDAOSeguir
import com.example.minichef_v1.bd.dao.usuario.DAOUsuario
import com.example.minichef_v1.bd.dao.usuario.IDAOUsuario
import com.example.minichef_v1.bd.modelo.Publicacion

class AutorViewModel(private val idAutor:String,private val idUsuario:String, admin:Boolean):ViewModel() {

    private val daoPublicacion:IDAOPublicacion=DAOPublicacion()
    private val daoSeguir:IDAOSeguir=DAOSeguir()
    private val daoUsuario:IDAOUsuario=DAOUsuario()

    private val _lista=MutableLiveData<List<Publicacion>>().apply {
        value= emptyList()
    }
    val lista:LiveData<List<Publicacion>> = _lista

    private val _leSigue=MutableLiveData<Boolean>().apply {
        value=false
    }
    val leSigue:LiveData<Boolean> = _leSigue

    init {
        daoPublicacion.getPublicacionesPorUsuario(this,idAutor,admin)
        daoSeguir.leSigue(this,idAutor,idUsuario)
    }

    fun setLista(publicaciones: List<Publicacion>) {
        _lista.value=publicaciones
    }

    fun setLeSigue(bool:Boolean){
        _leSigue.value=bool
    }

    fun seguir(){
        daoSeguir.seguir(idAutor,idUsuario)
        _leSigue.value=true
    }

    fun dejarSeguir(){
        daoSeguir.dejaSeguir(idAutor,idUsuario)
        _leSigue.value=false
    }

    fun banearAutor(idAutor: String){
        daoUsuario.banearUsuario(idAutor)
    }

    fun unbanearAutor(idAutor: String){
        daoUsuario.unbanearUsuario(idAutor)
    }
}

@Suppress("UNCHECKED_CAST")
class AutorViewModelFactory(private val idAutor:String,private val idUsuario:String,private val admin:Boolean): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AutorViewModel(idAutor,idUsuario,admin) as T
    }
}