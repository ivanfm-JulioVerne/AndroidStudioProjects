package com.example.kotlinbasedatosexamen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinbasedatosexamen.room.AppDB
import com.example.kotlinbasedatosexamen.room.entities.Alimento
import com.example.kotlinbasedatosexamen.room.entities.Ingrediente
import com.example.kotlinbasedatosexamen.room.entities.Receta

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var alimento= Alimento(nombre = "Filete de pollo", proteinas = 150, grasas = 75, hidratos = 30)
        AppDB.getAppDB(applicationContext)!!.daoAlimento().crearAlimento(alimento)

        val alimentos: List<Alimento>? =
            AppDB.getAppDB(applicationContext)!!.daoAlimento().verAlimentos()
            Log.d(":::Recetas", "Lectura de alimentos")
        if (alimentos != null) {
            for (al in alimentos) {
                Log.d(":::Recetas",
                    al.mostrar())
            }
        }

        var receta= Receta(nombre = "Filete de pollo cocinado")
        AppDB.getAppDB(applicationContext)!!.daoReceta().crearReceta(receta)

        val recetas: List<Receta>? =
            AppDB.getAppDB(applicationContext)!!.daoReceta().verRecetas()
        Log.d(":::Recetas", "Lectura de recetas")
        if (recetas != null) {
            for (re in recetas) {
                Log.d(":::Recetas",
                    re.mostrar())
            }
        }
        var ingrediente=Ingrediente(1,1,1)
        AppDB.getAppDB(applicationContext)!!.daoIngrediente().crearIngrediente(ingrediente)

        val ingredientes: List<Ingrediente>?=
            AppDB.getAppDB(applicationContext)!!.daoIngrediente().verIngredientes()
        Log.d(":::Recetas","Lectura de ingredientes")
        if (ingredientes != null){
            for (ing in ingredientes){
                var recetaIng=AppDB.getAppDB(applicationContext)!!.daoReceta().verReceta(ing.id_receta!!)
                Log.d(":::Recetas",ing.mostrar(applicationContext))
            }
        }

        AppDB.getAppDB(applicationContext)!!.daoIngrediente().borrarIngrediente(AppDB.getAppDB(applicationContext)!!.daoIngrediente().verIngrediente(1))
        AppDB.getAppDB(applicationContext)!!.daoAlimento().borrarAlimento(AppDB.getAppDB(applicationContext)!!.daoAlimento().verAlimento(1))
        AppDB.getAppDB(applicationContext)!!.daoReceta().borrarReceta(AppDB.getAppDB(applicationContext)!!.daoReceta().verReceta(1))
    }
}