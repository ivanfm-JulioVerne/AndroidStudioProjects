package com.example.minichef_v1.pantanllas.home.detallePublicacion.rvPasos

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.R

class PasosViewHolder(view: View):RecyclerView.ViewHolder(view) {

    var texto=view.findViewById<TextView>(R.id.tv_pasosItem)
    var num=view.findViewById<TextView>(R.id.tv_ordenItem)

    fun render(orden:Int,paso:String){
        texto.text=paso
        num.text=(""+orden)
    }
}