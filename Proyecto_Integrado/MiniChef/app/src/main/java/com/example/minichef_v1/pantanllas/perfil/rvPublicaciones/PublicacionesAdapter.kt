package com.example.minichef_v1.pantanllas.perfil.rvPublicaciones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.pantanllas.perfil.rvPublicaciones.PublicacionesViewHolder

class PublicacionesAdapter(private val publicaciones: List<Publicacion>): RecyclerView.Adapter<PublicacionesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PublicacionesViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return PublicacionesViewHolder(layoutInflater.inflate(R.layout.item_publicacion,parent,false))
    }

    override fun getItemCount(): Int = publicaciones.size

    override fun onBindViewHolder(holder: PublicacionesViewHolder, posicion: Int) {
        val item=publicaciones[posicion]
        holder.render(item)
    }

}