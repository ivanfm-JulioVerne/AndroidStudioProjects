package com.example.minichef_v1.pantanllas.home.detallePublicacion.rvPasos

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.R

class PasosViewHolder(view: View):RecyclerView.ViewHolder(view) {

    var texto: TextView =view.findViewById(R.id.tv_pasosItem)
    private var num: TextView =view.findViewById(R.id.tv_ordenItem)

    fun render(orden:Int,paso:String){
        texto.text=paso
        val orden=""+orden
        num.text=orden
    }
}