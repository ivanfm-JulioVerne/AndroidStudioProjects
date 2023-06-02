package com.example.minichef_v1.pantanllas.perfil.editar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.databinding.FragmentEditarBinding

class EditarFragment : Fragment() {

    private var _binding:FragmentEditarBinding?=null
    private val binding get() = _binding!!

    private var usuario=(activity as MainActivity).usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentEditarBinding.inflate(inflater,container,false)

        binding.etNicknameEditar.setText(usuario.nickname)
        binding.etNombreEditar.setText(usuario.nombre)
        binding.etDescripcionEditar.setText(usuario.biografia)

        binding.btnGuardarEditar.setOnClickListener {
            usuario.nickname=binding.etNicknameEditar.text.toString()
            usuario.nombre=binding.etNombreEditar.text.toString()
            usuario.biografia=binding.etDescripcionEditar.text.toString()

            (activity as MainActivity).usuario=usuario

        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar, container, false)
    }
}