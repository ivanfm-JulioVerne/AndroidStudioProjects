package com.example.prueba_input_console

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.InputStream
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tec: Scanner =Scanner(System.`in`)
        var a=tec.nextLine()

        println(":::" + a)
    }
}