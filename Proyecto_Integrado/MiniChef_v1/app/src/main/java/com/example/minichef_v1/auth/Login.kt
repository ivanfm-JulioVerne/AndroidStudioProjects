package com.example.minichef_v1.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.minichef_v1.AuthActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.databinding.FragmentLoginBinding

class Login : Fragment() {

    private var _binding:FragmentLoginBinding?=null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentLoginBinding.inflate(inflater,container,false)
        val root:View=binding.root

        val bRegistro: Button = binding.bLoginSignin
        bRegistro.setOnClickListener {
            (activity as AuthActivity).toRegistro()
        }

        // Inflate the layout for this fragment
        return root
    }
}