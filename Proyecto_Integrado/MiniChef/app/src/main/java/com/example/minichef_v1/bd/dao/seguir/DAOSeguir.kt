package com.example.minichef_v1.bd.dao.seguir

import com.example.minichef_v1.bd.dao.publicacion.DAOPublicacion
import com.example.minichef_v1.bd.dao.usuario.DAOUsuario
import com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.AutorViewModel
import com.example.minichef_v1.pantanllas.home.viewPager2.listadoPublicaciones.ListadoPublicacionesViewModel
import com.google.firebase.firestore.FirebaseFirestore

class DAOSeguir:IDAOSeguir {

    private val db=FirebaseFirestore.getInstance().collection("seguir")

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

    override fun getPublicacionesSiguiendo(
        idUsuario: String,
        listadoPublicacionesViewModel: ListadoPublicacionesViewModel,
        admin:Boolean
    ) {
        db.whereEqualTo("idUsuario",idUsuario).get().addOnCompleteListener {
            if (!it.result.isEmpty){
                val siguiendos= mutableListOf<String>()
                for (i in 0 until it.result.documents.size){
                    val result=it.result.documents[i]
                    //if (result.get("idAutor")!=idUsuario){
                        siguiendos.add(result.get("idAutor") as String)
                    //}
                }
                DAOPublicacion().getPublicacionesSiguiendo(siguiendos,listadoPublicacionesViewModel,admin)
            }
        }
    }

    override fun getPublicacionesSiguiendoTitulo(
        idUsuario: String,
        listadoPublicacionesViewModel: ListadoPublicacionesViewModel,
        titulo: String,
        admin:Boolean
    ) {
        db.whereEqualTo("idUsuario",idUsuario).get().addOnCompleteListener {
            if (!it.result.isEmpty){
                val siguiendos= mutableListOf<String>()
                for (i in 0 until it.result.documents.size){
                    val result=it.result.documents[i]
                    if (result.get("idAutor")!=idUsuario){
                        siguiendos.add(result.get("idAutor") as String)
                    }
                }
                DAOPublicacion().getPublicacionesSiguiendoTitulo(siguiendos,listadoPublicacionesViewModel,titulo,admin)
            }
        }
    }

    override fun getPublicacionesSiguiendoCategoria(
        idUsuario: String,
        listadoPublicacionesViewModel: ListadoPublicacionesViewModel,
        categoria: String,
        admin: Boolean
    ) {
        db.whereEqualTo("idUsuario",idUsuario).get().addOnCompleteListener {
            if (!it.result.isEmpty){
                val siguiendos= mutableListOf<String>()
                for (i in 0 until it.result.documents.size){
                    val result=it.result.documents[i]
                    if (result.get("idAutor")!=idUsuario){
                        siguiendos.add(result.get("idAutor") as String)
                    }
                }
                DAOPublicacion().getPublicacionesSiguiendoCategoria(siguiendos,listadoPublicacionesViewModel,categoria,admin)
            }
        }
    }
}