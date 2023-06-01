package com.example.minichef_v1.pantanllas.home.detallePublicacion.rvComentarios

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Comentario
import com.example.minichef_v1.pantanllas.home.detallePublicacion.DetallePublicacionViewModel

class ComentariosViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val nickname=view.findViewById<TextView>(R.id.tv_nicknameComentario)
    val texto=view.findViewById<TextView>(R.id.tv_textoComentario)
    val btnBanComentario=view.findViewById<Button>(R.id.btn_banComentario)
    val tvBan=view.findViewById<TextView>(R.id.tv_baneadoComentario)

    fun render(
        comentario: Comentario,
        mainActivity: MainActivity,
        detallePublicacionViewModel: DetallePublicacionViewModel
    ){
        nickname.text=comentario.nickname
        texto.text=comentario.texto

        if (mainActivity.usuario.admin){
            btnBanComentario.visibility=View.VISIBLE
        } else {
            btnBanComentario.visibility=View.GONE
        }

        if (comentario.baneado){
            tvBan.visibility=View.VISIBLE
            btnBanComentario.text="Unbanear"

        }else{
            tvBan.visibility=View.GONE
            btnBanComentario.text="Banear"
        }

        btnBanComentario.setOnClickListener {
            if (comentario.baneado){
                detallePublicacionViewModel.unbanearComentario(comentario.idComentario)
            }else{
                detallePublicacionViewModel.banearComentario(comentario.idComentario)
            }
        }
    }
}