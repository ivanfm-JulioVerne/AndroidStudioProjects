package com.example.minichef_v1

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.minichef_v1.bd.modelo.Categoria
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.bd.modelo.Usuario
import com.example.minichef_v1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var usuario:Usuario
    var publicacionSeleccionada:Publicacion=Publicacion()
    var categorias:List<Categoria> = emptyList()
    var categoriasTexto:List<String> = emptyList()
    var categoriasId:List<String> = emptyList()
    val viewModel= MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.categorias.observe(this) {
            categorias = it
        }

        viewModel.categoriasId.observe(this) {
            categoriasId = it
        }

        viewModel.categoriasTexto.observe(this) {
            categoriasTexto = it
        }

        window.setBackgroundDrawable(ColorDrawable(getColor(R.color.background_day)))

        val extras=this.intent.extras
        usuario=Usuario(
            extras!!.getString("idUsuario") ?:"",
            extras.getString("nickname") ?:"",
            extras.getString("nombre") ?:"",
            extras.getString("biografia"),
            extras.getBoolean("admin"),
            extras.getBoolean("baneado"),
            extras.getLong("num_seguidores"),
            extras.getLong("num_siguiendo"),
            extras.getLong("num_publicacion"),
        )

        if (supportActionBar!=null){
            this.supportActionBar!!.hide()
        }

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
    }

    fun goToAuthActivity(){
        val intent= Intent(this, AuthActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    fun deshabilitarNavBar(){
        runOnUiThread{
            binding.navView.visibility= View.GONE
        }
    }
    fun habilitarNavBar(){
        runOnUiThread{
            binding.navView.visibility= View.VISIBLE
        }
    }
}