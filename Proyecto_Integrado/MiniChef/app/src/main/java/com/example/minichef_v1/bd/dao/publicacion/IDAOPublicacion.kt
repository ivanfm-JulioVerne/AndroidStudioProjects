package com.example.minichef_v1.bd.dao.publicacion

import android.view.View
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.AutorViewModel
import com.example.minichef_v1.pantanllas.home.viewPager2.listadoPublicaciones.ListadoPublicacionesViewModel
import com.example.minichef_v1.pantanllas.perfil.PerfilViewModel

interface IDAOPublicacion {
    fun crearPublicacion(publicacion: Publicacion,view: View)

    fun getMasPopulares(homeViewModel: ListadoPublicacionesViewModel, admin:Boolean)
    fun getPublicacionesPorUsuario(perfilViewModel: PerfilViewModel, idUsuario: String)
    fun getPublicacionesPorUsuario(autorViewHolder: AutorViewModel, idUsuario: String,admin: Boolean)

    fun getPublicacionesSiguiendo(idUsuarios: List<String>,listadoPublicacionesViewModel: ListadoPublicacionesViewModel,admin:Boolean)

    fun getPublicacionesSiguiendoTitulo(idUsuarios: List<String>,listadoPublicacionesViewModel: ListadoPublicacionesViewModel,titulo: String,admin:Boolean)

    fun getPublicacionesSiguiendoCategoria(idUsuarios: List<String>,listadoPublicacionesViewModel: ListadoPublicacionesViewModel,categoria: String,admin:Boolean)

    fun darLike(idPublicacion:String,idUsuario: String)

    fun quitarLike(idPublicacion:String,idUsuario: String)

    fun buscarPorCategoria(homeViewModel: ListadoPublicacionesViewModel,categoria:String,admin: Boolean)
    fun buscarPorTitulo(homeViewModel: ListadoPublicacionesViewModel, titulo: String,admin: Boolean)

    fun banPublicacion(id:String)

    fun banPublicacionesPorUsuario(id:String)

    fun unbanPublicacion(id:String)

    fun unbanPublicacionesPorUsuario(id:String)

    fun borrarPublicacion(idPublicacion:String,url:String)

    fun borrarPublicacionPorIdUsuario(idUsuario: String)

    fun editarPublicacion(publicacion: Publicacion)
}