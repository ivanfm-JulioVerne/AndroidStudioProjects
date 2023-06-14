package com.example.minichef_v1.pantanllas.home.viewPager2.listadoPublicaciones.rvPublicaciones

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
import com.example.minichef_v1.pantanllas.home.viewPager2.listadoPublicaciones.ListadoPublicacionesFragment

class PublicacionesViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val titulo: TextView =view.findViewById(R.id.itemPublicacionTitulo)
    val descripcion: TextView =view.findViewById(R.id.itemDescripcionPublicacion)
    private val imagen: ImageView =view.findViewById(R.id.itemIvPublicacion)
    private val tvBan: TextView =view.findViewById(R.id.tv_baneadoItemPublicacion)

    fun render(publicacion: Publicacion){
        titulo.text=publicacion.titulo
        descripcion.text=publicacion.descripcion
        if (publicacion.imagen!=""){
            Log.d(":::Entra","")
            Glide.with(imagen.context).load(publicacion.imagen!!).into(imagen)
        }else{
            imagen.setImageResource(R.drawable.splashscreem)
        }

        if (publicacion.baneado){
            tvBan.visibility=View.VISIBLE
        }else{
            tvBan.visibility=View.GONE
        }


        itemView.setOnClickListener{
            (view.findFragment<ListadoPublicacionesFragment>().activity as MainActivity).publicacionSeleccionada=publicacion
            view.findNavController().navigate(R.id.action_navigation_home_to_detallePublicacionFragment)
        }
    }

}