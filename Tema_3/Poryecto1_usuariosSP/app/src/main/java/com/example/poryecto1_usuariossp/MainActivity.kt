package com.example.poryecto1_usuariossp

import android.os.BugreportManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.poryecto1_usuariossp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter:UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter=UserAdapter(getUsers())
        linearLayoutManager=LinearLayoutManager(this)

        binding.recyclerView.apply {
            layoutManager=linearLayoutManager
            adapter=userAdapter
        }
    }

    private fun getUsers():MutableList<User>{
        val users=mutableListOf<User>()


        users.add(User(1,"Pepe",""))
        users.add(User(2,"Luísa",""))
        users.add(User(3,"David",""))
        users.add(User(4,"Pepe",""))
        users.add(User(5,"Luísa",""))
        users.add(User(6,"David",""))

        return users

    }
}