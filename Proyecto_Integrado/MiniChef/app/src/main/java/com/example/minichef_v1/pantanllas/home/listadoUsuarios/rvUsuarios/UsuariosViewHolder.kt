package com.example.minichef_v1.pantanllas.home.listadoUsuarios.rvUsuarios

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Usuario
import com.example.minichef_v1.pantanllas.home.listadoUsuarios.ListadoUsuariosFragment

class UsuariosViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val nickname: TextView =view.findViewById(R.id.tv_Usuario)
    val baneado: TextView = view.findViewById(R.id.tv_baneadoItemUsuario)

    fun render(usuario: Usuario){
        nickname.text=usuario.nickname

        if (usuario.baneado){
            baneado.visibility=View.VISIBLE
        }else{
            baneado.visibility=View.GONE
        }

        itemView.setOnClickListener{
            val idUsuario=(view.findFragment<ListadoUsuariosFragment>().activity as MainActivity).usuario.id_usuario
            if (usuario.id_usuario!=idUsuario) {
                val bundle = Bundle()
                bundle.putString("idAutor", usuario.id_usuario)
                bundle.putString("nickname", usuario.nickname)
                bundle.putString("nombre", usuario.nombre)
                bundle.putString("biografia", usuario.biografia)
                bundle.putBoolean("admin", usuario.admin)
                bundle.putBoolean("baneado", usuario.baneado)
                bundle.putLong("num_seguidores", usuario.num_seguidores)
                bundle.putLong("num_Siguiendo", usuario.num_siguiendo)
                bundle.putLong("num_publicacion", usuario.num_publicacion)
                view.findNavController()
                    .navigate(R.id.action_listadoUsuariosFragment_to_autorFragment, bundle)
            }else{
                view.findNavController().navigate(R.id.action_listadoUsuariosFragment_to_navigation_perfil)
            }
        }
    }

}