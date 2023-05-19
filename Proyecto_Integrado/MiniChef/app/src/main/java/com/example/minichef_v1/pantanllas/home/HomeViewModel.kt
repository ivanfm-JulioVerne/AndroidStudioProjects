package com.example.minichef_v1.pantanllas.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minichef_v1.bd.dao.DAOPublicacion
import com.example.minichef_v1.bd.modelo.Publicacion

class HomeViewModel : ViewModel() {

    private val daoPublicacion=DAOPublicacion()

    private val _lista = MutableLiveData<List<Publicacion>>().apply {
        value = emptyList()
    }
    val lista: LiveData<List<Publicacion>> = _lista

    init {
        super.onCleared()
        daoPublicacion.getMasPopulares(this)
    }

    fun setLista(publicaciones:List<Publicacion>){
        _lista.value=publicaciones
    }
}