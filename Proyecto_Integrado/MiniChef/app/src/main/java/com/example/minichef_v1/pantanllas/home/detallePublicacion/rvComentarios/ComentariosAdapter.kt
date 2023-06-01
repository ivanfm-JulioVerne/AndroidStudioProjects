package com.example.minichef_v1.pantanllas.home.detallePublicacion.rvComentarios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Comentario
import com.example.minichef_v1.pantanllas.home.detallePublicacion.DetallePublicacionViewModel

class ComentariosAdapter(private val comentarios:List<Comentario>,private val mainActivity: MainActivity,private val detallePublicacionViewModel: DetallePublicacionViewModel):
    RecyclerView.Adapter<ComentariosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentariosViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ComentariosViewHolder(layoutInflater.inflate(R.layout.item_comentario,parent,false))
    }

    override fun getItemCount(): Int = comentarios.size

    override fun onBindViewHolder(holder: ComentariosViewHolder, position: Int) {
        holder.render(comentarios[position],mainActivity,detallePublicacionViewModel)
    }
}