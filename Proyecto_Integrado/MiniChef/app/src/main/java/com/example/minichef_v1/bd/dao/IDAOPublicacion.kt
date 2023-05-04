package com.example.minichef_v1.bd.dao

import android.view.View
import com.example.minichef_v1.bd.modelo.Publicacion

interface IDAOPublicacion {
    public fun crearPublicacion(publicacion: Publicacion,view: View)
}