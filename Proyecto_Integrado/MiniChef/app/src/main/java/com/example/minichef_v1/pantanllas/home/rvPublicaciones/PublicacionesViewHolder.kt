package com.example.minichef_v1.pantanllas.home.rvPublicaciones

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
import com.example.minichef_v1.pantanllas.home.HomeFragment

class PublicacionesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val titulo=view.findViewById<TextView>(R.id.itemPublicacionTitulo)
    val descripcion=view.findViewById<TextView>(R.id.itemDescripcionPublicacion)
    val imagen=view.findViewById<ImageView>(R.id.itemIvPublicacion)
    val view=view

    fun render(publicacion: Publicacion){
        titulo.text=publicacion.titulo
        descripcion.text=publicacion.descripcion
        if (publicacion.imagen!=""){
            Log.d(":::Entra","")
            Glide.with(imagen.context).load(publicacion.imagen!!).into(imagen)
        }

        itemView.setOnClickListener{
            (view.findFragment<HomeFragment>().activity as MainActivity).publicacionSeleccionada=publicacion
            view.findFragment<HomeFragment>().findNavController().navigate(R.id.action_navigation_home_to_detallePublicacionFragment)
        }
    }

}