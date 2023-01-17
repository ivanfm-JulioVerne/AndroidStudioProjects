package com.example.kotlinbasedatosexamen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinbasedatosexamen.room.AppDB
import com.example.kotlinbasedatosexamen.room.entities.Alimento
import com.example.kotlinbasedatosexamen.room.entities.Receta

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var alimento= Alimento(nombre = "Filete de pollo", proteinas = 150, grasas = 75, hidratos = 30)
        //AppDB.getAppDB(applicationContext)!!.daoAlimento().crearAlimento(alimento)

        val alimentos: List<Alimento>? =
            AppDB.getAppDB(applicationContext)!!.daoAlimento().verAlimentos()
            Log.d(":::Recetas", "Lectura de alimentos")
        if (alimentos != null) {
            for (al in alimentos) {
                Log.d(":::Recetas",
                    "\nId: ${al.id_alimento}" +
                            "\nNombre: ${al.nombre}" +
                            "\nGrasas: ${al.grasas}" +
                            "\nProteinas: ${al.proteinas}" +
                            "\nHidratos: ${al.hidratos}")
            }
        }

       // var receta= Receta(nombre = "Filete de pollo cocinado")
        //AppDB.getAppDB(applicationContext)!!.daoReceta().crearReceta(receta)

        val recetas: List<Receta>? =
            AppDB.getAppDB(applicationContext)!!.daoReceta().verRecetas()
        Log.d(":::Recetas", "Lectura de recetas")
        if (recetas != null) {
            for (re in recetas) {
                Log.d(":::Recetas",
                    "\nId: ${re.id_receta}" +
                            "\nNombre: ${re.nombre}")
            }
        }
    }
}