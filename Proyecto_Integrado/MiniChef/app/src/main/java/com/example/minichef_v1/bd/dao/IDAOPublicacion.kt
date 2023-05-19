package com.example.minichef_v1.bd.dao

import android.view.View
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.pantanllas.home.HomeViewModel

interface IDAOPublicacion {
    public fun crearPublicacion(publicacion: Publicacion,view: View)

    public fun getMasPopulares(homeViewModel: HomeViewModel)
}