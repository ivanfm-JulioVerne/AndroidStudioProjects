package com.example.minichef_v1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.minichef_v1.auth.Login

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        supportFragmentManager.beginTransaction().add(R.id.contenedor_auth,Login()).commit();
    }

    public fun toRegistro(){
        Log.d(":::Cambia a registro","")
    }

    public fun toLogin(){

    }
}