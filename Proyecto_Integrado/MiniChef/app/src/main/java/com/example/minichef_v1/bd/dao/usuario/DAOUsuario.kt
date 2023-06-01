package com.example.minichef_v1.bd.dao.usuario

import android.view.View
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import com.example.minichef_v1.AuthActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.auth.Login
import com.example.minichef_v1.auth.NoAuth
import com.example.minichef_v1.bd.dao.comentario.DAOComentario
import com.example.minichef_v1.bd.dao.publicacion.DAOPublicacion
import com.example.minichef_v1.bd.modelo.Usuario
import com.example.minichef_v1.pantanllas.home.detallePublicacion.DetallePublicacionViewModel
import com.google.firebase.firestore.FirebaseFirestore

class DAOUsuario : IDAOUsuario {

    private val db = FirebaseFirestore.getInstance().collection("usuario")

    override fun crearUsuarioNoAdmin(usuario: Usuario) {
        db.document(usuario.id_usuario).set(
            hashMapOf(
                "nickname" to usuario.nickname,
                "nombre" to usuario.nombre,
                "biografia" to usuario.biografia,
                "admin" to usuario.admin,
                "baneado" to usuario.baneado,
                "num_seguidores" to usuario.num_seguidores,
                "num_siguiendo" to usuario.num_siguiendo,
                "num_publicacion" to usuario.num_publicacion
            )
        )
    }

    override fun comprobarUsuarioExiste(id: String,v:View) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists()){
                (v.findFragment<NoAuth>().activity as AuthActivity).goToMainActivity(
                    Usuario(
                        id,
                        it.result.get("nickname") as String,
                        it.result.get("nombre") as String,
                        it.result.get("biografia") as String,
                        it.result.get("admin") as Boolean,
                        it.result.get("baneado") as Boolean,
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
        val result=db.document(id).get().addOnCompleteListener {
            if (it.result.exists())
                (v.findFragment<Login>().activity as AuthActivity).goToMainActivity(
                    Usuario(
                        id,
                        it.result.get("nickname") as String,
                        it.result.get("nombre") as String,
                        it.result.get("biografia") as String,
                        it.result.get("admin") as Boolean,
                        it.result.get("baneado") as Boolean,
                        it.result.get("num_seguidores") as Long,
                        it.result.get("num_siguiendo") as Long,
                        it.result.get("num_publicacion") as Long
                    )
                )
        }
    }

    override fun getUsuarioByIdFromDetallePublicion(
        id: String,
        holder: DetallePublicacionViewModel
    ) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists()){
                val usuario:Usuario=Usuario(
                    id,
                    it.result.get("nickname") as String,
                    it.result.get("nombre") as String,
                    it.result.get("biografia") as String,
                    it.result.get("admin") as Boolean,
                    it.result.get("baneado") as Boolean,
                    it.result.get("num_seguidores") as Long,
                    it.result.get("num_siguiendo") as Long,
                    it.result.get("num_publicacion") as Long
                )
                holder.setAutor(usuario)
            }
        }
    }

    override fun anadeNuevaPublicacion(id: String) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists())
                db.document(id).set(
                    hashMapOf(
                        "nickname" to it.result.get("nickname") as String,
                        "nombre" to it.result.get("nombre") as String,
                        "biografia" to it.result.get("biografia") as String,
                        "admin" to it.result.get("admin") as Boolean,
                        "baneado" to it.result.get("baneado") as Boolean,
                        "num_seguidores" to it.result.get("num_seguidores") as Long,
                        "num_siguiendo" to it.result.get("num_siguiendo") as Long,
                        "num_publicacion" to (it.result.get("num_publicacion") as Long+1)
                ))
        }
    }

    override fun anadeNuevaSeguidor(id: String) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists())
                db.document(id).set(
                    hashMapOf(
                        "nickname" to it.result.get("nickname") as String,
                        "nombre" to it.result.get("nombre") as String,
                        "biografia" to it.result.get("biografia") as String,
                        "admin" to it.result.get("admin") as Boolean,
                        "baneado" to it.result.get("baneado") as Boolean,
                        "num_seguidores" to (it.result.get("num_seguidores") as Long+1),
                        "num_siguiendo" to it.result.get("num_siguiendo") as Long,
                        "num_publicacion" to it.result.get("num_publicacion") as Long
                    ))
        }
    }

    override fun anadeNuevaSiguiendo(id: String) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists())
                db.document(id).set(
                    hashMapOf(
                        "nickname" to it.result.get("nickname") as String,
                        "nombre" to it.result.get("nombre") as String,
                        "biografia" to it.result.get("biografia") as String,
                        "admin" to it.result.get("admin") as Boolean,
                        "baneado" to it.result.get("baneado") as Boolean,
                        "num_seguidores" to it.result.get("num_seguidores") as Long,
                        "num_siguiendo" to (it.result.get("num_siguiendo") as Long+1),
                        "num_publicacion" to it.result.get("num_publicacion") as Long
                    ))
        }
    }

    override fun quitaNuevaPublicacion(id: String) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists())
                db.document(id).set(
                    hashMapOf(
                        "nickname" to it.result.get("nickname") as String,
                        "nombre" to it.result.get("nombre") as String,
                        "biografia" to it.result.get("biografia") as String,
                        "admin" to it.result.get("admin") as Boolean,
                        "baneado" to it.result.get("baneado") as Boolean,
                        "num_seguidores" to it.result.get("num_seguidores") as Long,
                        "num_siguiendo" to it.result.get("num_siguiendo") as Long,
                        "num_publicacion" to (it.result.get("num_publicacion") as Long-1)
                    ))
        }
    }

    override fun quitaNuevaSeguidor(id: String) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists())
                db.document(id).set(
                    hashMapOf(
                        "nickname" to it.result.get("nickname") as String,
                        "nombre" to it.result.get("nombre") as String,
                        "biografia" to it.result.get("biografia") as String,
                        "admin" to it.result.get("admin") as Boolean,
                        "baneado" to it.result.get("baneado") as Boolean,
                        "num_seguidores" to (it.result.get("num_seguidores") as Long-1),
                        "num_siguiendo" to it.result.get("num_siguiendo") as Long,
                        "num_publicacion" to it.result.get("num_publicacion") as Long
                    ))
        }
    }

    override fun quitaNuevaSiguiendo(id: String) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists())
                db.document(id).set(
                    hashMapOf(
                        "nickname" to it.result.get("nickname") as String,
                        "nombre" to it.result.get("nombre") as String,
                        "biografia" to it.result.get("biografia") as String,
                        "admin" to it.result.get("admin") as Boolean,
                        "baneado" to it.result.get("baneado") as Boolean,
                        "num_seguidores" to it.result.get("num_seguidores") as Long,
                        "num_siguiendo" to (it.result.get("num_siguiendo") as Long-1),
                        "num_publicacion" to it.result.get("num_publicacion") as Long
                    ))
        }
    }

    override fun banearUsuario(id: String) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists()){
                db.document(id).set(
                    hashMapOf(
                        "nickname" to it.result.get("nickname") as String,
                        "nombre" to it.result.get("nombre") as String,
                        "biografia" to it.result.get("biografia") as String,
                        "admin" to it.result.get("admin") as Boolean,
                        "baneado" to true,
                        "num_seguidores" to it.result.get("num_seguidores") as Long,
                        "num_siguiendo" to (it.result.get("num_siguiendo") as Long),
                        "num_publicacion" to it.result.get("num_publicacion") as Long
                    )
                )
                DAOPublicacion().banPublicacionesPorUsuario(id)
                DAOComentario().banComentarioPorUsuario(id)
            }
        }
    }

    override fun unbanearUsuario(id: String) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists()){
                db.document(id).set(
                    hashMapOf(
                        "nickname" to it.result.get("nickname") as String,
                        "nombre" to it.result.get("nombre") as String,
                        "biografia" to it.result.get("biografia") as String,
                        "admin" to it.result.get("admin") as Boolean,
                        "baneado" to false,
                        "num_seguidores" to it.result.get("num_seguidores") as Long,
                        "num_siguiendo" to (it.result.get("num_siguiendo") as Long),
                        "num_publicacion" to it.result.get("num_publicacion") as Long
                    )
                )
                DAOPublicacion().unbanPublicacionesPorUsuario(id)
                DAOComentario().unbanComentarioPorUsuario(id)
            }
        }
    }
}