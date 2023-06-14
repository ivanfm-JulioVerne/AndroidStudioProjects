package com.example.minichef_v1.bd.dao.usuario

import android.view.View
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import com.example.minichef_v1.AuthActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.auth.Login
import com.example.minichef_v1.auth.NoAuth
import com.example.minichef_v1.bd.dao.comentario.DAOComentario
import com.example.minichef_v1.bd.dao.like.DAOLike
import com.example.minichef_v1.bd.dao.publicacion.DAOPublicacion
import com.example.minichef_v1.bd.dao.seguir.DAOSeguir
import com.example.minichef_v1.bd.modelo.Usuario
import com.example.minichef_v1.pantanllas.home.detallePublicacion.DetallePublicacionViewModel
import com.example.minichef_v1.pantanllas.home.listadoUsuarios.ListadoUsuariosViewModel
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
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
                val usuario=Usuario(
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
                if (usuario.baneado){
                    (v.findFragment<NoAuth>().activity as AuthActivity).usuarioBaneado()
                }else{
                    (v.findFragment<NoAuth>().activity as AuthActivity).goToMainActivity(usuario)
                }
            }else{
                v.findNavController().navigate(R.id.action_noAuth_to_nuevoUsuarioGoogle)
            }
        }
    }

    override fun getUsuarioLogueado(id: String, authActivity: AuthActivity) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists()){
                val usuario=Usuario(
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

                if(usuario.baneado){
                    authActivity.usuarioBaneado()
                }else{
                    authActivity.goToMainActivity(usuario)
                }
            }
        }
    }

    override fun getUsuarioByIdFromLogin(id: String,v:View) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists()) {
                val usuario = Usuario(
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
                if (usuario.baneado){
                    (v.findFragment<Login>().activity as AuthActivity).usuarioBaneado()
                }
                else
                {
                    (v.findFragment<Login>().activity as AuthActivity).goToMainActivity(usuario)
                }
            }
        }
    }

    override fun getUsuarioByNickname(
        nickname: String,
        listadoUsuariosViewModel: ListadoUsuariosViewModel,
        admin: Boolean
    ) {
        if (admin){
            db.orderBy("num_seguidores").get().addOnCompleteListener {
                if (!it.result.isEmpty){
                    val usuarios = mutableListOf<Usuario>()
                    for (i in 0 until it.result.documents.size){
                        val result=it.result.documents[i]
                        if ((result.get("nickname") as String).contains(nickname,true)){
                            usuarios.add(Usuario(
                                result.id,
                                result.get("nickname") as String,
                                result.get("nombre") as String,
                                result.get("biografia") as String,
                                result.get("admin") as Boolean,
                                result.get("baneado") as Boolean,
                                result.get("num_seguidores") as Long,
                                result.get("num_siguiendo") as Long,
                                result.get("num_publicacion") as Long
                            ))
                        }
                    }
                    listadoUsuariosViewModel.setLista(usuarios)
                }
            }
        }else{
            db.whereEqualTo("baneado",false).orderBy("num_seguidores").limit(50).get().addOnCompleteListener {
                if (!it.result.isEmpty){
                    val usuarios = mutableListOf<Usuario>()
                    for (i in 0 until it.result.documents.size){
                        val result=it.result.documents[i]
                        if ((result.get("nickname") as String).contains(nickname,true)){
                            usuarios.add(Usuario(
                                result.id,
                                result.get("nickname") as String,
                                result.get("nombre") as String,
                                result.get("biografia") as String,
                                result.get("admin") as Boolean,
                                result.get("baneado") as Boolean,
                                result.get("num_seguidores") as Long,
                                result.get("num_siguiendo") as Long,
                                result.get("num_publicacion") as Long
                            ))
                        }
                    }
                    listadoUsuariosViewModel.setLista(usuarios)
                }
            }
        }
    }

    override fun getUsuariosPopulares(
        listadoUsuariosViewModel: ListadoUsuariosViewModel,
        admin: Boolean
    ) {
        if (admin){
            db.orderBy("num_seguidores").limit(50).get().addOnCompleteListener {
                if (!it.result.isEmpty){
                    val usuarios = mutableListOf<Usuario>()
                    for (i in 0 until it.result.documents.size){
                        val result=it.result.documents[i]
                        usuarios.add(Usuario(
                            result.id,
                            result.get("nickname") as String,
                            result.get("nombre") as String,
                            result.get("biografia") as String,
                            result.get("admin") as Boolean,
                            result.get("baneado") as Boolean,
                            result.get("num_seguidores") as Long,
                            result.get("num_siguiendo") as Long,
                            result.get("num_publicacion") as Long
                        ))
                    }
                    usuarios.reverse()
                    listadoUsuariosViewModel.setLista(usuarios)
                }
            }
        }else{
            db.whereEqualTo("baneado",false).orderBy("num_seguidores").limit(50).get().addOnCompleteListener {
                if (!it.result.isEmpty){
                    val usuarios = mutableListOf<Usuario>()
                    for (i in 0 until it.result.documents.size){
                        val result=it.result.documents[i]
                        usuarios.add(Usuario(
                            result.id,
                            result.get("nickname") as String,
                            result.get("nombre") as String,
                            result.get("biografia") as String,
                            result.get("admin") as Boolean,
                            result.get("baneado") as Boolean,
                            result.get("num_seguidores") as Long,
                            result.get("num_siguiendo") as Long,
                            result.get("num_publicacion") as Long
                        ))
                    }
                    listadoUsuariosViewModel.setLista(usuarios)
                }
            }
        }
    }

    override fun getUsuarioByIdFromDetallePublicion(
        id: String,
        holder: DetallePublicacionViewModel
    ) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists()){
                val usuario=Usuario(
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

    override fun editarPerfil(usuario: Usuario) {
        if (usuario.id_usuario != ""){
            db.document(usuario.id_usuario).set(usuario)
        }
    }

    override fun eliminarUsuario(idUsuario: String) {
        db.document(idUsuario).delete().addOnCompleteListener {
            DAOPublicacion().borrarPublicacionPorIdUsuario(idUsuario)
            DAOComentario().borrarComentarioPorIdUsuario(idUsuario)
        }
    }
}