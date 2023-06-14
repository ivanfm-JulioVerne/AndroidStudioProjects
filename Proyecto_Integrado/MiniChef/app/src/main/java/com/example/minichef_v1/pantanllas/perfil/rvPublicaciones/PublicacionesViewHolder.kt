package com.example.minichef_v1.pantanllas.perfil.rvPublicaciones

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.pantanllas.perfil.PerfilFragment

class PublicacionesViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val titulo: TextView =view.findViewById(R.id.itemPublicacionTitulo)
    val descripcion: TextView =view.findViewById(R.id.itemDescripcionPublicacion)
    private val imagen: ImageView =view.findViewById(R.id.itemIvPublicacion)

    fun render(publicacion: Publicacion){
        titulo.text=publicacion.titulo
        descripcion.text=publicacion.descripcion
        if (publicacion.imagen!=""){
            Log.d(":::Entra","")
            Glide.with(imagen.context).load(publicacion.imagen!!).into(imagen)
        }

        itemView.setOnClickListener{
            (view.findFragment<PerfilFragment>().activity as MainActivity).publicacionSeleccionada=publicacion
            view.findFragment<PerfilFragment>().findNavController().navigate(R.id.action_navigation_perfil_to_detallePublicacionFragment)
        }
    }

}