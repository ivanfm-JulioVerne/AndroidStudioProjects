package com.example.minichef_v1.bd.dao

import android.view.View
import androidx.fragment.app.findFragment
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.pantanllas.nuevo.NuevoFragment
import com.google.firebase.firestore.FirebaseFirestore

class DAOPublicacion:IDAOPublicacion {

    private val db = FirebaseFirestore.getInstance()

    override fun crearPublicacion(publicacion: Publicacion,view: View) {
        db.collection("publicacion").add(publicacion).addOnCompleteListener {
            if(it.isSuccessful){
                view.findFragment<NuevoFragment>().showAlertPublicacionCreada()
            }
        }
    }
}