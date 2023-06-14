package com.example.minichef_v1

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.minichef_v1.bd.dao.usuario.DAOUsuario
import com.example.minichef_v1.bd.modelo.Usuario
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Theme_MiniChef_v1)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        isStoragePermissionGranted()

        if (supportActionBar!=null){
            this.supportActionBar!!.hide()
        }

        if (FirebaseAuth.getInstance().currentUser != null){
            DAOUsuario().getUsuarioLogueado(FirebaseAuth.getInstance().currentUser!!.uid,this)
        }

        //Log.d(":::Usuario Logueado",FirebaseAuth.getInstance().currentUser?.uid ?:"Vacio")

        //supportFragmentManager.beginTransaction().add(R.id.contenedor_auth,NoAuth()).commit();
    }

    fun goToMainActivity(usuario: Usuario){
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("idUsuario",usuario.id_usuario)
        intent.putExtra("nickname",usuario.nickname)
        intent.putExtra("nombre",usuario.nombre)
        intent.putExtra("biografia",usuario.biografia)
        intent.putExtra("admin",usuario.admin)
        intent.putExtra("baneado",usuario.baneado)
        intent.putExtra("num_seguidores",usuario.num_seguidores)
        intent.putExtra("num_publicacion",usuario.num_publicacion)
        intent.putExtra("num_siguiendo",usuario.num_siguiendo)
        startActivity(intent)
    }

    fun usuarioBaneado(){
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Este usuario está baneado")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }

    private fun isStoragePermissionGranted(): Boolean {
        return if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED
            ) {
                true
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
                false
            }
        }
}