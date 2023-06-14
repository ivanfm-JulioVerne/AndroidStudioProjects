package com.example.minichef_v1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minichef_v1.bd.dao.categoria.DAOCategoria
import com.example.minichef_v1.bd.modelo.Categoria

class MainActivityViewModel:ViewModel() {

    private val daoCategoria=DAOCategoria()

    private val _categoriasTexto = MutableLiveData<List<String>>().apply {
        value = emptyList()
    }
    val categoriasTexto: LiveData<List<String>> = _categoriasTexto

    private val _categoriasId = MutableLiveData<List<String>>().apply {
        value = emptyList()
    }
    val categoriasId: LiveData<List<String>> = _categoriasId

    private val _categorias = MutableLiveData<List<Categoria>>().apply {
        value = emptyList()
    }
    val categorias: LiveData<List<Categoria>> = _categorias

    init {
        cargarCategorias()
    }

    private fun cargarCategorias(){
        daoCategoria.getCategorias(this)
    }

    fun setCategoriasTexto(categorias:List<String>){
        _categoriasTexto.value=categorias
    }

    fun setCategoriasId(categorias:List<String>){
        _categoriasId.value=categorias
    }

    fun setCategorias(categorias:List<Categoria>){
        _categorias.value=categorias
    }
}