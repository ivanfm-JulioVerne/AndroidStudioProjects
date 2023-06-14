package com.example.minichef_v1.pantanllas.home.detallePublicacion.rvComentarios

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Comentario
import com.example.minichef_v1.pantanllas.home.detallePublicacion.DetallePublicacionViewModel

class ComentariosViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val nickname: TextView =view.findViewById(R.id.tv_nicknameComentario)
    val texto: TextView =view.findViewById(R.id.tv_textoComentario)
    private val btnBanComentario: Button =view.findViewById(R.id.btn_banComentario)
    private val tvBan: TextView =view.findViewById(R.id.tv_baneadoComentario)

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
            //btnBanComentario.text="Unbanear"
            btnBanComentario.text=view.resources.getText(R.string.desbanear)
        }else{
            tvBan.visibility=View.GONE
            //btnBanComentario.text="Banear"
            btnBanComentario.text=view.resources.getText(R.string.banear)
        }

        btnBanComentario.setOnClickListener {
            if (comentario.baneado){
                tvBan.visibility=View.GONE
                //btnBanComentario.text="Banear"
                btnBanComentario.text=view.resources.getText(R.string.banear)
                detallePublicacionViewModel.unbanearComentario(comentario.idComentario)
                comentario.baneado=false
            }else{
                detallePublicacionViewModel.banearComentario(comentario.idComentario)
                tvBan.visibility=View.VISIBLE
                btnBanComentario.text=view.resources.getText(R.string.desbanear)
                //btnBanComentario.text="Unbanear"
                comentario.baneado=true
            }
        }
    }
}