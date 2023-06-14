package com.example.minichef_v1.bd.dao.categoria

import com.example.minichef_v1.MainActivityViewModel
import com.example.minichef_v1.bd.modelo.Categoria
import com.google.firebase.firestore.FirebaseFirestore

class DAOCategoria:IDAOCategoria {

    private val db=FirebaseFirestore.getInstance().collection("categoria")

    override fun getCategorias(mainActivityViewModel: MainActivityViewModel) {
        db.orderBy("texto").get().addOnCompleteListener {
            if (!it.result.isEmpty){
                val categorias = mutableListOf<Categoria>()
                val categoriasTexto = mutableListOf<String>()
                val categoriasId = mutableListOf<String>()
                for (i in 0 until it.result.documents.size){
                    val result=it.result.documents[i]
                    categorias.add(
                        Categoria(
                            result.id,
                            result.get("texto") as String
                        )
                    )
                    categoriasTexto.add(
                        result.get("texto") as String
                    )
                    categoriasId.add(
                        result.id
                    )
                }
                mainActivityViewModel.setCategoriasId(categoriasId)
                mainActivityViewModel.setCategoriasTexto(categoriasTexto)
                mainActivityViewModel.setCategorias(categorias)
            }
        }
    }
}