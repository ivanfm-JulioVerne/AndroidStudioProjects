package com.example.minichef_v1.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.minichef_v1.R
import com.example.minichef_v1.databinding.FragmentSigninBinding

class Signin : Fragment() {

    private var _binding:FragmentSigninBinding?=null

    private val binding get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentSigninBinding.inflate(inflater,container,false)
        val root=binding.root

        val bLogin: Button =binding.bLoginEmail2
        bLogin.setOnClickListener { v ->
            findNavController().navigate(R.id.action_signin_to_login)
        }

        // Inflate the layout for this fragment
        return root
    }
}