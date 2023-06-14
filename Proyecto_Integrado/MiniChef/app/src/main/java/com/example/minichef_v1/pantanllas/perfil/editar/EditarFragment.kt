package com.example.minichef_v1.pantanllas.perfil.editar

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.databinding.FragmentEditarBinding

class EditarFragment : Fragment() {

    private var _binding:FragmentEditarBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentEditarBinding.inflate(inflater,container,false)
        val root=binding.root

        val usuario=(activity as MainActivity).usuario
        val editarViewModel=EditarViewModel()

        binding.etNicknameEditar.setText(usuario.nickname)
        binding.etNombreEditar.setText(usuario.nombre)
        binding.etDescripcionEditar.setText(usuario.biografia)

        binding.btnGuardarEditar.setOnClickListener {
            if (binding.etNicknameEditar.text.toString() !=""){
                if (binding.etNombreEditar.text.toString() !=""){
                    usuario.nickname=binding.etNicknameEditar.text.toString()
                    usuario.nombre=binding.etNombreEditar.text.toString()
                    usuario.biografia=binding.etDescripcionEditar.text.toString()
                    (activity as MainActivity).usuario=usuario
                    editarViewModel.guardarUsuario(usuario)
                    findNavController().navigate(R.id.action_editarFragment_to_navigation_perfil)
                }else{
                    alertNombreVacio()
                }
            }else{
                alertNicknameVacio()
            }



        }


        // Inflate the layout for this fragment
        return root
    }

    private fun alertNicknameVacio(){
        AlertDialog.Builder(activity)
            .setMessage("El perfil tiene que tener un nickname")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }

    private fun alertNombreVacio(){
        AlertDialog.Builder(activity)
            .setMessage("El perfil tiene que tener un nombre")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }
}