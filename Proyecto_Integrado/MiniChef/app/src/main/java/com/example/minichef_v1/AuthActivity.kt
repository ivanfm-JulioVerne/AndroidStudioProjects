package com.example.minichef_v1

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.minichef_v1.bd.modelo.Usuario


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

        //supportFragmentManager.beginTransaction().add(R.id.contenedor_auth,NoAuth()).commit();
    }

    public fun goToMainActivity(usuario: Usuario){
        val intent = Intent(this, MainActivity::class.java)
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

    fun isStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
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
        } else { //permission is automatically granted on sdk<23 upon installation
            true
        }
    }
}