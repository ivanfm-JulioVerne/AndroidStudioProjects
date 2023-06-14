package com.example.minichef_v1.bd.dao.comentario

import com.example.minichef_v1.bd.modelo.Comentario
import com.example.minichef_v1.pantanllas.home.detallePublicacion.DetallePublicacionViewModel
import com.google.firebase.firestore.FirebaseFirestore

class DAOComentario : IDAOComentario {

    private val db = FirebaseFirestore.getInstance().collection("comentario")

    override fun anadirComentario(idPublicacion: String, idUsuario: String, texto: String, nickname: String) {
        db.add(
            hashMapOf(
                "idPublicacion" to idPublicacion,
                "idUsuario" to idUsuario,
                "baneado" to false,
                "texto" to texto,
                "nickname" to nickname
            )
        )
    }

    override fun banComentarioPorUsuario(idUsuario: String) {
        db.whereEqualTo("idUsuario",idUsuario).get().addOnCompleteListener {
            if (!it.result.isEmpty){
                for (i in 0 until it.result.documents.size){
                    banComentario(it.result.documents[i].id)
                }
            }
        }
    }

    override fun banComentario(id: String) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists()){
                db.document(id).set(
                    hashMapOf(
                        "idPublicacion" to it.result.get("idPublicacion"),
                        "idUsuario" to it.result.get("idUsuario"),
                        "texto" to it.result.get("texto"),
                        "baneado" to true,
                        "nickname" to it.result.get("nickname")
                    )
                )
            }
        }
    }

override fun unbanComentarioPorUsuario(idUsuario: String) {
    db.whereEqualTo("idUsuario",idUsuario).get().addOnCompleteListener {
        if (!it.result.isEmpty){
            for (i in 0 until it.result.documents.size){
                unbanComentario(it.result.documents[i].id)
            }
        }
    }
}

    override fun unbanComentario(id: String) {
        db.document(id).get().addOnCompleteListener {
            if (it.result.exists()){
                db.document(id).set(
                    hashMapOf(
                        "idPublicacion" to it.result.get("idPublicacion"),
                        "idUsuario" to it.result.get("idUsuario"),
                        "texto" to it.result.get("texto"),
                        "baneado" to false,
                        "nickname" to it.result.get("nickname")
                    )
                )
            }
        }
    }

    override fun borrarComentarioPorIdUsuario(idUsuario: String) {
        db.whereEqualTo("idUsuario",idUsuario).get().addOnCompleteListener {
            if (!it.result.isEmpty){
                for (i in 0 until it.result.documents.size){
                    val result=it.result.documents[i]
                    borrarComentario(result.id)
                }
            }
        }
    }

    override fun borrarComentario(id: String) {
        db.document(id).delete()
    }

    override fun mostrarComentariosByPublicacion(detallePublicacionViewModel: DetallePublicacionViewModel,idPublicacion: String,admin:Boolean) {
        if (idPublicacion!=""){
            if (admin){
                db.whereEqualTo("idPublicacion", idPublicacion).get().addOnCompleteListener {
                    if (!it.result.isEmpty) {
                        val comentarios = mutableListOf<Comentario>()
                        for (i in 0 until it.result.documents.size) {
                            val result = it.result.documents[i]
                            comentarios.add(
                                Comentario(
                                    result.id,
                                    result.get("idPublicacion") as String,
                                    result.get("idUsuario") as String,
                                    result.get("texto") as String,
                                    result.get("baneado") as Boolean,
                                    result.get("nickname") as String
                                )
                            )
                        }
                        detallePublicacionViewModel.setComentarios(comentarios)
                    }
                }
            }else{
                db.whereEqualTo("baneado",false).whereEqualTo("idPublicacion", idPublicacion).get().addOnCompleteListener {
                    if (!it.result.isEmpty) {
                        val comentarios = mutableListOf<Comentario>()
                        for (i in 0 until it.result.documents.size) {
                            val result = it.result.documents[i]
                            comentarios.add(
                                Comentario(
                                    result.id,
                                    result.get("idPublicacion") as String,
                                    result.get("idUsuario") as String,
                                    result.get("texto") as String,
                                    result.get("baneado") as Boolean,
                                    result.get("nickname") as String
                                )
                            )
                        }
                        detallePublicacionViewModel.setComentarios(comentarios)
                    }
                }
            }
        }
    }
}