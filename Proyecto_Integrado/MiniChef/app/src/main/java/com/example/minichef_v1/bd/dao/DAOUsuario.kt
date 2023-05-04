package com.example.minichef_v1.bd.dao

import android.util.Log
import android.view.View
import androidx.fragment.app.findFragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.minichef_v1.AuthActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.auth.Login
import com.example.minichef_v1.auth.NoAuth
import com.example.minichef_v1.bd.modelo.Usuario
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class DAOUsuario : IDAOUsuario {

    private val db = FirebaseFirestore.getInstance()

    override fun crearUsuarioNoAdmin(usuario: Usuario) {
        db.collection("usuario").document(usuario.id_usuario).set(
            hashMapOf(
                "nickname" to usuario.nickname,
                "nombre" to usuario.nombre,
                "biografia" to usuario.biografia,
                "admin" to usuario.admin,
                "num_seguidores" to usuario.num_seguidores,
                "num_siguiendo" to usuario.num_siguiendo,
                "num_publicacion" to usuario.num_publicacion
            )
        )
    }

    override fun comprobarUsuarioExiste(id: String,v:View) {
        db.collection("usuario").document(id).get().addOnCompleteListener {
            if (it.result.exists()){
                (v.findFragment<NoAuth>().activity as AuthActivity).goToMainActivity(
                    Usuario(
                        id,
                        it.result.get("nickname") as String,
                        it.result.get("nombre") as String,
                        it.result.get("biografia") as String,
                        it.result.get("admin") as Boolean,
                        it.result.get("num_seguidores") as Long,
                        it.result.get("num_siguiendo") as Long,
                        it.result.get("num_publicacion") as Long
                    )
                )
            }else{
                v.findNavController().navigate(R.id.action_noAuth_to_nuevoUsuarioGoogle)
            }
        }
    }

    override fun getUsuarioByIdFromLogin(id: String,v:View) {
        val result=db.collection("usuario").document(id).get().addOnCompleteListener {
            if (it.result.exists())
                (v.findFragment<Login>().activity as AuthActivity).goToMainActivity(
                    Usuario(
                        id,
                        it.result.get("nickname") as String,
                        it.result.get("nombre") as String,
                        it.result.get("biografia") as String,
                        it.result.get("admin") as Boolean,
                        it.result.get("num_seguidores") as Long,
                        it.result.get("num_siguiendo") as Long,
                        it.result.get("num_publicacion") as Long
                    )
                )
        }
    }


}