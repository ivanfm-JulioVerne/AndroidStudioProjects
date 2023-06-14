package com.example.minichef_v1.pantanllas.home.listadoUsuarios.rvUsuarios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Usuario

class UsuariosAdapter(private val usuarios: List<Usuario>): RecyclerView.Adapter<UsuariosViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return UsuariosViewHolder(layoutInflater.inflate(R.layout.item_usuario,parent,false))
    }

    override fun getItemCount(): Int =usuarios.size

    override fun onBindViewHolder(holder: UsuariosViewHolder, position: Int) {
        holder.render(usuarios[position])
    }
}