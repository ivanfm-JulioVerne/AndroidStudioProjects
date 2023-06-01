package com.example.minichef_v1.bd.dao.seguir

import com.example.minichef_v1.bd.dao.publicacion.DAOPublicacion
import com.example.minichef_v1.bd.dao.usuario.DAOUsuario
import com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.AutorViewModel
import com.google.firebase.firestore.FirebaseFirestore

class DAOSeguir:IDAOSeguir {

    val db=FirebaseFirestore.getInstance().collection("seguir")

    override fun seguir(idAutor: String, idUsuario: String) {
        db.add(
            hashMapOf(
                "idAutor" to idAutor,
                "idUsuario" to idUsuario
            )
        ).addOnCompleteListener {
            DAOUsuario().anadeNuevaSeguidor(idAutor)
            DAOUsuario().anadeNuevaSiguiendo(idUsuario)
        }
    }

    override fun dejaSeguir(idAutor: String, idUsuario: String) {
        db.whereEqualTo("idAutor",idAutor).whereEqualTo("idUsuario",idUsuario).get().addOnCompleteListener {
            if (!it.result.isEmpty){
                for (i in 0 until it.result.documents.size){
                    db.document(it.result.documents[i].id).delete()
                }
                DAOUsuario().quitaNuevaSeguidor(idAutor)
                DAOUsuario().quitaNuevaSiguiendo(idUsuario)
            }
        }
    }

    override fun leSigue(autorViewModel: AutorViewModel, idAutor: String, idUsuario: String) {
        db.whereEqualTo("idAutor",idAutor).whereEqualTo("idUsuario",idUsuario).get().addOnCompleteListener {
            if (!it.result.isEmpty){
                autorViewModel.setLeSigue(true)
            }else{
                autorViewModel.setLeSigue(false)
            }
        }
    }
}