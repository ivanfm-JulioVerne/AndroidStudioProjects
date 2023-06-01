package com.example.minichef_v1.bd.dao.publicacion

import android.view.View
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.pantanllas.home.HomeViewModel
import com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.AutorViewModel
import com.example.minichef_v1.pantanllas.perfil.PerfilViewModel

interface IDAOPublicacion {
    public fun crearPublicacion(publicacion: Publicacion,view: View)

    public fun getMasPopulares(homeViewModel: HomeViewModel,admin:Boolean)
    fun getPublicacionesPorUsuario(perfilViewModel: PerfilViewModel, idUsuario: String)
    fun getPublicacionesPorUsuario(autorViewHolder: AutorViewModel, idUsuario: String,admin: Boolean)

    fun darLike(idPublicacion:String,idUsuario: String)

    fun quitarLike(idPublicacion:String,idUsuario: String)

    fun buscarPorCategoria(homeViewModel: HomeViewModel,categoria:String,admin: Boolean)
    fun buscarPorTitulo(homeViewModel: HomeViewModel, titulo: String,admin: Boolean)

    fun banPublicacion(id:String)

    fun banPublicacionesPorUsuario(id:String)

    fun unbanPublicacion(id:String)

    fun unbanPublicacionesPorUsuario(id:String)
}