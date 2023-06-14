package com.example.minichef_v1.pantanllas.home.detallePublicacion.rvIngredientes

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.R

class IngredientesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var texto: TextView =view.findViewById(R.id.tv_pasosItem)

    fun render(ingrediente:String){
        texto.text=ingrediente
    }
}