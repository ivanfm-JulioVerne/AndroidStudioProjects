package com.example.minichef_v1.pantanllas.home.viewPager2.listadoPublicaciones

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minichef_v1.bd.dao.publicacion.DAOPublicacion
import com.example.minichef_v1.bd.dao.seguir.DAOSeguir
import com.example.minichef_v1.bd.modelo.Publicacion

class ListadoPublicacionesViewModel(private val admin:Boolean) : ViewModel()  {
    private val daoPublicacion= DAOPublicacion()
    private val daoSeguir=DAOSeguir()

    private val _lista = MutableLiveData<List<Publicacion>>().apply {
        value = emptyList()
    }
    val lista: LiveData<List<Publicacion>> = _lista


    init {
        super.onCleared()
        //buscar50mejores(this)
    }

    fun buscar50mejores(listadoPublicacionesViewModel: ListadoPublicacionesViewModel){
        daoPublicacion.getMasPopulares(listadoPublicacionesViewModel,admin)
    }

    fun buscarSiguiendo(listadoPublicacionesViewModel: ListadoPublicacionesViewModel,idUsuario: String){
        daoSeguir.getPublicacionesSiguiendo(idUsuario,listadoPublicacionesViewModel,admin)
    }

    fun buscarSiguiendoTitulo(listadoPublicacionesViewModel: ListadoPublicacionesViewModel,idUsuario: String,titulo: String){
        daoSeguir.getPublicacionesSiguiendoTitulo(idUsuario,listadoPublicacionesViewModel,titulo,admin)
    }

    fun buscarSiguiendoCategoria(listadoPublicacionesViewModel: ListadoPublicacionesViewModel,idUsuario: String,categoria: String){
        daoSeguir.getPublicacionesSiguiendoCategoria(idUsuario,listadoPublicacionesViewModel,categoria,admin)
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
class ListadoPublicacionesViewModelFactory(private val admin:Boolean): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListadoPublicacionesViewModel(admin) as T
    }
}