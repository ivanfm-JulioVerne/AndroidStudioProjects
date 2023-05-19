package com.example.minichef_v1

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.bd.modelo.Usuario
import com.example.minichef_v1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    public lateinit var usuario:Usuario
    var publicacionesHome:List<Publicacion> = emptyList()
    var publicacionSeleccionada:Publicacion=Publicacion()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_perfil
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val extras=this.intent.extras
        usuario=Usuario(
            extras!!.getString("idUsuario") ?:"",
            extras!!.getString("nickname") ?:"",
            extras!!.getString("nombre") ?:"",
            extras!!.getString("biografia"),
            extras!!.getBoolean("admin"),
            extras!!.getLong("num_seguidores"),
            extras!!.getLong("num_publicacion"),
            extras!!.getLong("num_siguiendo"),
        )
        usuario.print()
    }
}