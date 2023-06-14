package com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.rvPublicaciones

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.AutorFragment

class PublicacionesAutorViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val titulo: TextView =view.findViewById(R.id.itemPublicacionTitulo)
    val descripcion: TextView =view.findViewById(R.id.itemDescripcionPublicacion)
    private val imagen: ImageView =view.findViewById(R.id.itemIvPublicacion)
    val baneado:TextView= view.findViewById(R.id.tv_baneadoItemPublicacion)

    fun render(publicacion: Publicacion){
        titulo.text=publicacion.titulo
        descripcion.text=publicacion.descripcion
        if (publicacion.imagen!=""){
            Log.d(":::Entra","")
            Glide.with(imagen.context).load(publicacion.imagen!!).into(imagen)
        }

        if (publicacion.baneado){
            baneado.visibility=View.VISIBLE
        }else{
            baneado.visibility=View.GONE
        }

        itemView.setOnClickListener{
            (view.findFragment<AutorFragment>().activity as MainActivity).publicacionSeleccionada=publicacion
            view.findNavController().navigate(R.id.action_autorFragment_to_detallePublicacionFragment)
        }
    }
}