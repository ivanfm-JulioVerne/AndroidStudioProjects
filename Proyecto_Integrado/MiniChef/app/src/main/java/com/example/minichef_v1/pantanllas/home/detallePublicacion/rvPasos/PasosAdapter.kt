package com.example.minichef_v1.pantanllas.home.detallePublicacion.rvPasos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.R
import com.example.minichef_v1.pantanllas.home.detallePublicacion.rvIngredientes.IngredientesViewHolder

class PasosAdapter(private val pasos:ArrayList<String>): RecyclerView.Adapter<PasosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasosViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return PasosViewHolder(layoutInflater.inflate(R.layout.item_paso,parent,false))
    }

    override fun getItemCount(): Int=pasos.size

    override fun onBindViewHolder(holder: PasosViewHolder, position: Int) {
        holder.render(position+1,pasos[position])
    }

}