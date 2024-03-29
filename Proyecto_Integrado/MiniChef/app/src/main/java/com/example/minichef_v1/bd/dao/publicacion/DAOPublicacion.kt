package com.example.minichef_v1.bd.dao.publicacion

import android.util.Log
import android.view.View
import androidx.fragment.app.findFragment
import com.example.minichef_v1.bd.dao.like.DAOLike
import com.example.minichef_v1.bd.dao.like.IDAOLike
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.AutorViewModel
import com.example.minichef_v1.pantanllas.home.viewPager2.listadoPublicaciones.ListadoPublicacionesViewModel
import com.example.minichef_v1.pantanllas.nuevo.NuevoFragment
import com.example.minichef_v1.pantanllas.perfil.PerfilViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

@Suppress("UNCHECKED_CAST")
class DAOPublicacion: IDAOPublicacion {

    private val db = FirebaseFirestore.getInstance().collection("publicacion")

    override fun crearPublicacion(publicacion: Publicacion,view: View) {
        db.add(publicacion).addOnCompleteListener {
            if(it.isSuccessful){
                view.findFragment<NuevoFragment>().showAlertPublicacionCreada()
            }
        }
    }

    override fun getMasPopulares(homeViewModel: ListadoPublicacionesViewModel, admin:Boolean) {
        if (admin) {
            db.orderBy("num_likes").limit(50).get().addOnCompleteListener {
                if (!it.result.isEmpty) {
                    val publicaciones = mutableListOf<Publicacion>()
                    for (i in 0 until it.result.documents.size) {
                        val result = it.result.documents[i]
                        publicaciones.add(
                            Publicacion(
                                result.id,
                                result.get("titulo") as String,
                                result.get("descripcion") as String,
                                result.get("ingredientes") as ArrayList<String>,
                                result.get("pasos") as ArrayList<String>,
                                result.get("imagen") as String,
                                result.get("num_likes") as Long,
                                result.get("baneado") as Boolean,
                                result.get("id_usuario") as String,
                                result.get("id_categoria") as ArrayList<String>
                            )
                        )
                    }
                    publicaciones.reverse()
                    homeViewModel.setLista(publicaciones)
                }
            }
        }else{
                db.whereEqualTo("baneado",false).orderBy("num_likes").limit(50).get().addOnCompleteListener {
                    if (it.isSuccessful) {
                        val publicaciones = mutableListOf<Publicacion>()
                        for (i in 0 until it.result.documents.size) {
                            val result = it.result.documents[i]
                            publicaciones.add(
                                Publicacion(
                                    result.id,
                                    result.get("titulo") as String,
                                    result.get("descripcion") as String,
                                    result.get("ingredientes") as ArrayList<String>,
                                    result.get("pasos") as ArrayList<String>,
                                    result.get("imagen") as String,
                                    result.get("num_likes") as Long,
                                    result.get("baneado") as Boolean,
                                    result.get("id_usuario") as String,
                                    result.get("id_categoria") as ArrayList<String>
                                )
                            )
                        }
                        publicaciones.reverse()
                        homeViewModel.setLista(publicaciones)
                    }else{
                        Log.d(":::Error",it.exception.toString())
                    }
                }
        }
    }

    override fun getPublicacionesPorUsuario(perfilViewModel: PerfilViewModel, idUsuario: String) {
        if ( idUsuario!=""){
                db.whereEqualTo("id_usuario",idUsuario).get().addOnCompleteListener{
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
                                result.get("baneado") as Boolean,
                                result.get("id_usuario") as String,
                                result.get("id_categoria") as ArrayList<String>))
                        }
                        perfilViewModel.setLista(publicaciones)
                    }
                }
                /*db.whereEqualTo("baneado",false).whereEqualTo("id_usuario",idUsuario).get().addOnCompleteListener{
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
                                result.get("baneado") as Boolean,
                                result.get("id_usuario") as String,
                                result.get("id_categoria") as ArrayList<String>))
                        }
                        perfilViewModel.setLista(publicaciones)
                    }

            }*/
        }
    }

    override fun getPublicacionesPorUsuario(autorViewHolder: AutorViewModel, idUsuario: String,admin:Boolean) {
        if ( idUsuario!=""){
            if (admin){
                db.whereEqualTo("id_usuario",idUsuario).get().addOnCompleteListener{
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
                                result.get("baneado") as Boolean,
                                result.get("id_usuario") as String,
                                result.get("id_categoria") as ArrayList<String>))
                        }
                        autorViewHolder.setLista(publicaciones)
                    }
                }
            }else{
                db.whereEqualTo("baneado",false).whereEqualTo("id_usuario",idUsuario).get().addOnCompleteListener{
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
                                result.get("baneado") as Boolean,
                                result.get("id_usuario") as String,
                                result.get("id_categoria") as ArrayList<String>))
                        }
                        autorViewHolder.setLista(publicaciones)
                    }
                }
            }
        }
    }

    override fun getPublicacionesSiguiendo(
        idUsuarios: List<String>,
        listadoPublicacionesViewModel: ListadoPublicacionesViewModel,
        admin:Boolean
    ) {
        if (admin){
            val publicaciones = mutableListOf<Publicacion>()
            var count=0
            for (i in idUsuarios.indices){
                db.whereEqualTo("id_usuario",idUsuarios[i]).get().addOnCompleteListener {
                    if (!it.result.isEmpty){
                        for (j in 0 until it.result.documents.size){
                            val result= it.result.documents[j]
                            publicaciones.add(Publicacion(result.id,
                                result.get("titulo") as String,
                                result.get("descripcion") as String,
                                result.get("ingredientes") as ArrayList<String>,
                                result.get("pasos") as ArrayList<String>,
                                result.get("imagen") as String,
                                result.get("num_likes") as Long,
                                result.get("baneado") as Boolean,
                                result.get("id_usuario") as String,
                                result.get("id_categoria") as ArrayList<String>))
                        }
                    }
                    if (count==idUsuarios.size-1){
                        listadoPublicacionesViewModel.setLista(publicaciones.sortedByDescending {
                                publicacion -> publicacion.num_likes
                            }
                        )
                    }
                    count++
                }
            }
        }else{
            val publicaciones = mutableListOf<Publicacion>()
            for (i in idUsuarios.indices){
                db.whereEqualTo("baneado",false).whereEqualTo("id_usuario",idUsuarios[i]).get().addOnCompleteListener {
                    if (!it.result.isEmpty){
                        for (j in 0 until it.result.documents.size){
                            val result= it.result.documents[j]
                            publicaciones.add(Publicacion(result.id,
                                result.get("titulo") as String,
                                result.get("descripcion") as String,
                                result.get("ingredientes") as ArrayList<String>,
                                result.get("pasos") as ArrayList<String>,
                                result.get("imagen") as String,
                                result.get("num_likes") as Long,
                                result.get("baneado") as Boolean,
                                result.get("id_usuario") as String,
                                result.get("id_categoria") as ArrayList<String>))
                        }
                    }
                    if (i==idUsuarios.size-1){
                        listadoPublicacionesViewModel.setLista(publicaciones.sortedByDescending {
                                publicacion -> publicacion.num_likes
                        }
                        )
                    }
                }
            }
        }
    }

    override fun getPublicacionesSiguiendoTitulo(
        idUsuarios: List<String>,
        listadoPublicacionesViewModel: ListadoPublicacionesViewModel,
        titulo: String,
        admin:Boolean
    ) {
        if (admin){
            val publicaciones = mutableListOf<Publicacion>()
            for (i in idUsuarios.indices){
                db.whereEqualTo("id_usuario",idUsuarios[i]).get().addOnCompleteListener {
                    if (!it.result.isEmpty){
                        for (j in 0 until it.result.documents.size){
                            val result= it.result.documents[j]
                            if ((result.get("titulo") as String).contains(titulo,false)){
                                publicaciones.add(Publicacion(result.id,
                                    result.get("titulo") as String,
                                    result.get("descripcion") as String,
                                    result.get("ingredientes") as ArrayList<String>,
                                    result.get("pasos") as ArrayList<String>,
                                    result.get("imagen") as String,
                                    result.get("num_likes") as Long,
                                    result.get("baneado") as Boolean,
                                    result.get("id_usuario") as String,
                                    result.get("id_categoria") as ArrayList<String>))
                            }
                        }

                    }
                    if (i==idUsuarios.size-1){
                        listadoPublicacionesViewModel.setLista(publicaciones.sortedByDescending {
                                publicacion -> publicacion.num_likes
                        }
                        )
                    }
                }
            }
        }else{
            val publicaciones = mutableListOf<Publicacion>()
            for (i in idUsuarios.indices){
                db.whereEqualTo("baneado",false).whereEqualTo("id_usuario",idUsuarios[i]).get().addOnCompleteListener {
                    if (!it.result.isEmpty){
                        for (j in 0 until it.result.documents.size){
                            val result= it.result.documents[j]
                            if ((result.get("titulo") as String).contains(titulo,false)){
                                publicaciones.add(Publicacion(result.id,
                                    result.get("titulo") as String,
                                    result.get("descripcion") as String,
                                    result.get("ingredientes") as ArrayList<String>,
                                    result.get("pasos") as ArrayList<String>,
                                    result.get("imagen") as String,
                                    result.get("num_likes") as Long,
                                    result.get("baneado") as Boolean,
                                    result.get("id_usuario") as String,
                                    result.get("id_categoria") as ArrayList<String>))
                            }
                        }

                    }
                    if (i==idUsuarios.size-1){
                        listadoPublicacionesViewModel.setLista(publicaciones.sortedByDescending {
                                publicacion -> publicacion.num_likes
                        }
                        )
                    }
                }
            }
        }
    }

    override fun getPublicacionesSiguiendoCategoria(
        idUsuarios: List<String>,
        listadoPublicacionesViewModel: ListadoPublicacionesViewModel,
        categoria: String,
        admin: Boolean
    ) {
        if (admin){
            db.get().addOnCompleteListener {
                if (!it.result.isEmpty){
                    val publicaciones = mutableListOf<Publicacion>()
                    for (i in 0 until it.result.documents.size){
                        val result=it.result.documents[i]
                        val idCategoria:ArrayList<String> =result.get("id_categoria") as ArrayList<String>
                        var categoriaExiste=false
                        for (j in 0 until idCategoria.size){
                            if (idCategoria[j]==categoria){
                                categoriaExiste=true
                            }
                        }
                        if (categoriaExiste && idUsuarios.contains(result.get("id_usuario"))){
                            publicaciones.add(Publicacion(result.id,
                                result.get("titulo") as String,
                                result.get("descripcion") as String,
                                result.get("ingredientes") as ArrayList<String>,
                                result.get("pasos") as ArrayList<String>,
                                result.get("imagen") as String,
                                result.get("num_likes") as Long,
                                result.get("baneado") as Boolean,
                                result.get("id_usuario") as String,
                                result.get("id_categoria") as ArrayList<String>))
                        }
                    }
                    listadoPublicacionesViewModel.setLista(publicaciones.sortedByDescending {
                            publicacion -> publicacion.num_likes
                    })
                }
            }
        }else{
            db.whereEqualTo("baneado",false).get().addOnCompleteListener {
                if (!it.result.isEmpty){
                    val publicaciones = mutableListOf<Publicacion>()
                    for (i in 0 until it.result.documents.size){
                        val result=it.result.documents[i]
                        val idCategoria:ArrayList<String> =result.get("id_categoria") as ArrayList<String>
                        var categoriaExiste=false
                        for (j in 0 until idCategoria.size){
                            if (idCategoria[j]==categoria){
                                categoriaExiste=true
                            }
                        }
                        if (categoriaExiste && idUsuarios.contains(result.get("id_usuario"))){
                            publicaciones.add(Publicacion(result.id,
                                result.get("titulo") as String,
                                result.get("descripcion") as String,
                                result.get("ingredientes") as ArrayList<String>,
                                result.get("pasos") as ArrayList<String>,
                                result.get("imagen") as String,
                                result.get("num_likes") as Long,
                                result.get("baneado") as Boolean,
                                result.get("id_usuario") as String,
                                result.get("id_categoria") as ArrayList<String>))
                        }
                    }
                    listadoPublicacionesViewModel.setLista(publicaciones.sortedByDescending {
                            publicacion -> publicacion.num_likes
                    })
                }
            }
        }
    }

    override fun darLike(idPublicacion: String, idUsuario: String) {
        val daoLike: IDAOLike = DAOLike()
        db.document(idPublicacion).get().addOnCompleteListener {
            if(it.result.exists()){
                db.document(idPublicacion).set(
                    hashMapOf(
                        "titulo" to it.result.get("titulo") as String,
                        "descripcion" to it.result.get("descripcion") as String,
                        "ingredientes" to it.result.get("ingredientes") as ArrayList<String>,
                        "pasos" to it.result.get("pasos") as ArrayList<String>,
                        "imagen" to it.result.get("imagen") as String,
                        "num_likes" to (it.result.get("num_likes") as Long)+1,
                        "baneado" to (it.result.get("baneado") as Boolean),
                        "id_usuario" to it.result.get("id_usuario") as String,
                        "id_categoria" to it.result.get("id_categoria") as ArrayList<String>,
                    )
                ).addOnSuccessListener {
                    daoLike.anadirLike(idPublicacion,idUsuario)
                }
            }
        }
    }

    override fun quitarLike(idPublicacion: String, idUsuario: String) {
        val daoLike: IDAOLike = DAOLike()
        db.document(idPublicacion).get().addOnCompleteListener {
            if(it.result.exists()){
                db.document(idPublicacion).set(
                    hashMapOf(
                        "titulo" to it.result.get("titulo") as String,
                        "descripcion" to it.result.get("descripcion") as String,
                        "ingredientes" to it.result.get("ingredientes") as ArrayList<String>,
                        "pasos" to it.result.get("pasos") as ArrayList<String>,
                        "imagen" to it.result.get("imagen") as String,
                        "num_likes" to (it.result.get("num_likes") as Long)-1,
                        "baneado" to (it.result.get("baneado") as Boolean),
                        "id_usuario" to it.result.get("id_usuario") as String,
                        "id_categoria" to it.result.get("id_categoria") as ArrayList<String>,
                    )
                ).addOnSuccessListener {
                    daoLike.quitarLike(idPublicacion,idUsuario)
                }
            }
        }
    }

    override fun buscarPorTitulo(homeViewModel: ListadoPublicacionesViewModel,titulo: String,admin:Boolean) {
        if (admin){
            db.orderBy("num_likes").get().addOnCompleteListener {
                if (!it.result.isEmpty){
                    val publicaciones= mutableListOf<Publicacion>()
                    for (i in 0 until it.result.documents.size){
                        val result=it.result.documents[i]
                        if ((it.result.documents[i].get("titulo") as String).contains(titulo,true)){
                            publicaciones.add(Publicacion(result.id,
                                result.get("titulo") as String,
                                result.get("descripcion") as String,
                                result.get("ingredientes") as ArrayList<String>,
                                result.get("pasos") as ArrayList<String>,
                                result.get("imagen") as String,
                                result.get("num_likes") as Long,
                                result.get("baneado") as Boolean,
                                result.get("id_usuario") as String,
                                result.get("id_categoria") as ArrayList<String>))
                        }
                    }
                    homeViewModel.setLista(publicaciones)
                }
            }
        }else{
            db.whereEqualTo("baneado",false).orderBy("num_likes").get().addOnCompleteListener {
                if (!it.result.isEmpty){
                    val publicaciones= mutableListOf<Publicacion>()
                    for (i in 0 until it.result.documents.size){
                        val result=it.result.documents[i]
                        if ((it.result.documents[i].get("titulo") as String).contains(titulo,true)){
                            publicaciones.add(Publicacion(result.id,
                                result.get("titulo") as String,
                                result.get("descripcion") as String,
                                result.get("ingredientes") as ArrayList<String>,
                                result.get("pasos") as ArrayList<String>,
                                result.get("imagen") as String,
                                result.get("num_likes") as Long,
                                result.get("baneado") as Boolean,
                                result.get("id_usuario") as String,
                                result.get("id_categoria") as ArrayList<String>))
                        }
                    }
                    homeViewModel.setLista(publicaciones)
                }
            }
        }
    }

    override fun banPublicacion(id: String) {
        db.document(id).get().addOnCompleteListener {
            if(it.result.exists()){
                db.document(id).set(
                    hashMapOf(
                        "titulo" to it.result.get("titulo") as String,
                        "descripcion" to it.result.get("descripcion") as String,
                        "ingredientes" to it.result.get("ingredientes") as ArrayList<String>,
                        "pasos" to it.result.get("pasos") as ArrayList<String>,
                        "imagen" to it.result.get("imagen") as String,
                        "num_likes" to (it.result.get("num_likes") as Long),
                        "baneado" to true,
                        "id_usuario" to it.result.get("id_usuario") as String,
                        "id_categoria" to it.result.get("id_categoria") as ArrayList<String>,
                    )
                )
            }
        }
    }

    override fun banPublicacionesPorUsuario(id: String) {
        db.whereEqualTo("id_usuario", id).get().addOnCompleteListener {
            if (!it.result.isEmpty){
                for (i in 0 until it.result.documents.size){
                    banPublicacion(it.result.documents[i].id)
                }
            }
        }
    }

    override fun unbanPublicacion(id: String) {
        db.document(id).get().addOnCompleteListener {
            if(it.result.exists()){
                db.document(id).set(
                    hashMapOf(
                        "titulo" to it.result.get("titulo") as String,
                        "descripcion" to it.result.get("descripcion") as String,
                        "ingredientes" to it.result.get("ingredientes") as ArrayList<String>,
                        "pasos" to it.result.get("pasos") as ArrayList<String>,
                        "imagen" to it.result.get("imagen") as String,
                        "num_likes" to (it.result.get("num_likes") as Long),
                        "baneado" to false,
                        "id_usuario" to it.result.get("id_usuario") as String,
                        "id_categoria" to it.result.get("id_categoria") as ArrayList<String>,
                    )
                )
            }
        }
    }

    override fun unbanPublicacionesPorUsuario(id: String) {
        db.whereEqualTo("id_usuario", id).get().addOnCompleteListener {
            if (!it.result.isEmpty){
                for (i in 0 until it.result.documents.size){
                    unbanPublicacion(it.result.documents[i].id)
                }
            }
        }
    }

    override fun borrarPublicacion(idPublicacion:String,url:String) {
        db.document(idPublicacion).delete().addOnSuccessListener {
            if (url!=""){
                FirebaseStorage.getInstance().getReferenceFromUrl(url).delete()
            }
        }
    }

    override fun borrarPublicacionPorIdUsuario(idUsuario: String) {
        db.whereEqualTo("id_usuario",idUsuario).get().addOnCompleteListener {
            if (!it.result.isEmpty){
                for (i in 0 until it.result.documents.size){
                    val result=it.result.documents[i]
                    val url=result.get("imagen") as String
                    borrarPublicacion(result.id,url)
                }
            }
        }
    }

    override fun editarPublicacion(publicacion: Publicacion) {
        db.document(publicacion.id_publicacion?:"").set(publicacion)
    }

    override fun buscarPorCategoria(homeViewModel: ListadoPublicacionesViewModel,categoria: String,admin:Boolean) {
        if (admin){
            db.orderBy("num_likes").get().addOnCompleteListener {
                if (!it.result.isEmpty){
                    val publicaciones= mutableListOf<Publicacion>()
                    for (i in 0 until it.result.documents.size){
                        val result=it.result.documents[i]
                        val idCategoria:ArrayList<String> =result.get("id_categoria") as ArrayList<String>
                        var categoriaExiste=false
                        for (j in 0 until idCategoria.size){
                            if (idCategoria[j]==categoria){
                                categoriaExiste=true
                            }
                        }
                        if (categoriaExiste){
                            publicaciones.add(Publicacion(result.id,
                                result.get("titulo") as String,
                                result.get("descripcion") as String,
                                result.get("ingredientes") as ArrayList<String>,
                                result.get("pasos") as ArrayList<String>,
                                result.get("imagen") as String,
                                result.get("num_likes") as Long,
                                result.get("baneado") as Boolean,
                                result.get("id_usuario") as String,
                                result.get("id_categoria") as ArrayList<String>))
                        }
                    }
                    homeViewModel.setLista(publicaciones)
                }
            }
        }else{
            db.whereEqualTo("baneado",false).orderBy("num_likes").get().addOnCompleteListener {
                if (!it.result.isEmpty){
                    val publicaciones= mutableListOf<Publicacion>()
                    for (i in 0 until it.result.documents.size){
                        val result=it.result.documents[i]
                        val idCategoria:ArrayList<String> =result.get("id_categoria") as ArrayList<String>
                        var categoriaExiste=false
                        for (j in 0 until idCategoria.size){
                            if (idCategoria[j]==categoria){
                                categoriaExiste=true
                            }
                        }
                        if (categoriaExiste){
                            publicaciones.add(Publicacion(result.id,
                                result.get("titulo") as String,
                                result.get("descripcion") as String,
                                result.get("ingredientes") as ArrayList<String>,
                                result.get("pasos") as ArrayList<String>,
                                result.get("imagen") as String,
                                result.get("num_likes") as Long,
                                result.get("baneado") as Boolean,
                                result.get("id_usuario") as String,
                                result.get("id_categoria") as ArrayList<String>))
                        }
                    }
                    homeViewModel.setLista(publicaciones)
                }
            }
        }
    }
}