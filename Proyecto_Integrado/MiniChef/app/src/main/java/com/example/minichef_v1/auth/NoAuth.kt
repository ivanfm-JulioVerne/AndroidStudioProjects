package com.example.minichef_v1.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.minichef_v1.R
import com.example.minichef_v1.databinding.FragmentNoAuthBinding

class NoAuth : Fragment() {

    private var _binding:FragmentNoAuthBinding?=null;
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentNoAuthBinding.inflate(inflater,container,false);
        val root=binding.root;

        val bLogin:Button=binding.bLoginEmail
        bLogin.setOnClickListener {v ->
            v.findNavController().navigate(R.id.action_noAuth_to_login)
        }
        val bRegistro:Button=binding.bRegEmail
        bRegistro.setOnClickListener { v->
            v.findNavController().navigate(R.id.action_noAuth_to_signin)
        }

        // Inflate the layout for this fragment
        return root
    }
}