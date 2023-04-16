package com.example.minichef_v1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.minichef_v1.auth.Login
import com.example.minichef_v1.auth.NoAuth

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Theme_MiniChef_v1)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //supportFragmentManager.beginTransaction().add(R.id.contenedor_auth,NoAuth()).commit();
    }
}