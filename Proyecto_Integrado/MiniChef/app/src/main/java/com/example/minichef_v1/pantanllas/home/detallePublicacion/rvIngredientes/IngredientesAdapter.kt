package com.example.minichef_v1.pantanllas.home.detallePublicacion.rvIngredientes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.R

class IngredientesAdapter(private val ingredientes: ArrayList<String>): RecyclerView.Adapter<IngredientesViewHolder>()   {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientesViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return IngredientesViewHolder(layoutInflater.inflate(R.layout.item_ingrediente,parent,false))
    }

    override fun onBindViewHolder(holder: IngredientesViewHolder, position: Int) {
        val item=ingredientes[position]
        holder.render(item)
    }

    override fun getItemCount(): Int=ingredientes.size
}