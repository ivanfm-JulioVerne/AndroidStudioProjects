package com.example.minichef_v1.pantanllas.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minichef_v1.bd.dao.publicacion.DAOPublicacion
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.AutorViewModel

class HomeViewModel(private val admin:Boolean) : ViewModel() {

    private val daoPublicacion= DAOPublicacion()

    private val _lista = MutableLiveData<List<Publicacion>>().apply {
        value = emptyList()
    }
    val lista: LiveData<List<Publicacion>> = _lista

    init {
        super.onCleared()
        buscar50mejores(this)
    }

    fun buscar50mejores(homeViewModel: HomeViewModel){
        daoPublicacion.getMasPopulares(homeViewModel,admin)
    }

    fun buscarPorCategoria(categoria:String){
        daoPublicacion.buscarPorCategoria(this,categoria,admin)
    }

    fun buscarPorTitulo(titulo:String){
        daoPublicacion.buscarPorTitulo(this,titulo,admin)
    }

    fun setLista(publicaciones:List<Publicacion>){
        _lista.value=publicaciones
    }
}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val admin:Boolean): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(admin) as T
    }
}