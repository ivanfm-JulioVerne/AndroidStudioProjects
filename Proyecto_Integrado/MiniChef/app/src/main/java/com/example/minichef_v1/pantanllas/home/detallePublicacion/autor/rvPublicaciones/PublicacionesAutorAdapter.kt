package com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.rvPublicaciones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.pantanllas.home.detallePublicacion.rvComentarios.ComentariosViewHolder

class PublicacionesAutorAdapter(private val publicaciones:List<Publicacion>):RecyclerView.Adapter<PublicacionesAutorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicacionesAutorViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return PublicacionesAutorViewHolder(layoutInflater.inflate(R.layout.item_publicacion,parent,false))
    }

    override fun onBindViewHolder(holder: PublicacionesAutorViewHolder, position: Int) {
        holder.render(publicaciones[position])
    }

    override fun getItemCount(): Int =publicaciones.size
}