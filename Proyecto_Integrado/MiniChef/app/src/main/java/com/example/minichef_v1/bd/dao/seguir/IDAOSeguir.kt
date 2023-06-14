package com.example.minichef_v1.bd.dao.seguir

import com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.AutorViewModel
import com.example.minichef_v1.pantanllas.home.viewPager2.listadoPublicaciones.ListadoPublicacionesViewModel

interface IDAOSeguir {

    fun seguir(idAutor:String,idUsuario:String)

    fun dejaSeguir(idAutor: String,idUsuario: String)

    fun leSigue(autorViewModel: AutorViewModel,idAutor:String,idUsuario:String)

    fun getPublicacionesSiguiendo(idUsuario: String,listadoPublicacionesViewModel: ListadoPublicacionesViewModel,admin:Boolean)

    fun getPublicacionesSiguiendoTitulo(idUsuario: String,listadoPublicacionesViewModel: ListadoPublicacionesViewModel,titulo: String,admin:Boolean)

    fun getPublicacionesSiguiendoCategoria(idUsuario: String,listadoPublicacionesViewModel: ListadoPublicacionesViewModel,categoria: String,admin:Boolean)
}