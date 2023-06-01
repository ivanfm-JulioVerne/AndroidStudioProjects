package com.example.minichef_v1.bd.dao.like

import com.example.minichef_v1.pantanllas.home.detallePublicacion.DetallePublicacionViewModel
import com.google.firebase.firestore.FirebaseFirestore

class DAOLike: IDAOLike {

    val db=FirebaseFirestore.getInstance().collection("like")

    override fun anadirLike(idPublicacion: String, idUsuario: String) {
        db.add(
            hashMapOf(
                "idPublicacion" to idPublicacion,
                "idUsuario" to idUsuario
            )
        )
    }

    override fun quitarLike(idPublicacion: String, idUsuario: String) {
        db.whereEqualTo("idPublicacion",idPublicacion).whereEqualTo("idUsuario",idUsuario).get().addOnCompleteListener {
            if (!it.result.isEmpty){
                for (i in 0 until it.result.documents.size){
                    val idLike:String=it.result.documents[i].id
                    db.document(idLike).delete()
                }
            }
        }
    }

    override fun comprobarLike(
        detallePublicacionViewModel: DetallePublicacionViewModel,
        idPublicacion: String,
        idUsuario: String
    ) {
        db.whereEqualTo("idPublicacion",idPublicacion).whereEqualTo("idUsuario",idUsuario).get().addOnCompleteListener{
            if (!it.result.isEmpty){
                detallePublicacionViewModel.setMeGusto(true)
            }else{
                detallePublicacionViewModel.setMeGusto(false)
            }
        }
    }
}