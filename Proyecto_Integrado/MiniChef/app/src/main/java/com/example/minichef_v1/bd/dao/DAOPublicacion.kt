package com.example.minichef_v1.bd.dao

import android.view.View
import androidx.fragment.app.findFragment
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.pantanllas.home.HomeViewModel
import com.example.minichef_v1.pantanllas.nuevo.NuevoFragment
import com.google.firebase.firestore.FirebaseFirestore

class DAOPublicacion:IDAOPublicacion {

    private val db = FirebaseFirestore.getInstance().collection("publicacion")

    override fun crearPublicacion(publicacion: Publicacion,view: View) {
        db.add(publicacion).addOnCompleteListener {
            if(it.isSuccessful){
                view.findFragment<NuevoFragment>().showAlertPublicacionCreada()
            }
        }
    }

    override fun getMasPopulares(homeViewModel: HomeViewModel) {
        db.orderBy("num_likes").limit(50).get().addOnCompleteListener {
            if (it.isSuccessful){
                val publicaciones = mutableListOf<Publicacion>()
                for (i in 0 until it.result.documents.size){
                    val result= it.result.documents[i]
                    publicaciones.add(Publicacion(result.id,
                        result.get("titulo") as String,
                        result.get("descripcion") as String,
                        result.get("ingredientes") as ArrayList<String>,
                        result.get("pasos") as ArrayList<String>,
                        result.get("imagen") as String,
                        result.get("num_likes") as Long,
                        result.get("id_usuario") as String,
                        result.get("id_categoria") as ArrayList<String>))
                }
                homeViewModel.setLista(publicaciones)
            }
        }
    }
}