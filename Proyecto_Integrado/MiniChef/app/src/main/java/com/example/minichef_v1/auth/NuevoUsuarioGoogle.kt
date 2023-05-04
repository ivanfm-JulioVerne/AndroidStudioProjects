package com.example.minichef_v1.auth

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.minichef_v1.AuthActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.dao.DAOUsuario
import com.example.minichef_v1.bd.dao.IDAOUsuario
import com.example.minichef_v1.bd.modelo.Usuario
import com.example.minichef_v1.databinding.FragmentNoAuthBinding
import com.example.minichef_v1.databinding.FragmentNuevoUsuarioGoogleBinding
import com.google.firebase.auth.FirebaseAuth

class NuevoUsuarioGoogle : Fragment() {

    private var _binding: FragmentNuevoUsuarioGoogleBinding?=null;
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

        _binding= FragmentNuevoUsuarioGoogleBinding.inflate(inflater,container,false)
        val root=binding.root;

        Log.d(":::Id",FirebaseAuth.getInstance().uid.toString())

        val bReg: Button =binding.bRegGoogle
        bReg.setOnClickListener {
            if (
                binding.editTextNicknameGoogle.text.isNotEmpty() &&
                binding.editTextNombreGoogle.text.isNotEmpty()
            ){
                Log.d(":::Nuevo Usuario",FirebaseAuth.getInstance().uid.toString())
                val daoUsuario: IDAOUsuario = DAOUsuario()
                val usuario: Usuario = Usuario(FirebaseAuth.getInstance().uid.toString(),
                    binding.editTextNicknameGoogle.text.toString(),
                    binding.editTextNombreGoogle.text.toString(),
                    binding.editTextBiografiaGoogle.text.toString()
                )
                daoUsuario.crearUsuarioNoAdmin(usuario)
                (activity as AuthActivity).goToMainActivity(usuario)
            }
        }

        val bCancelar:Button=binding.bCancelarGoogle
        bCancelar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_nuevoUsuarioGoogle_to_noAuth)
        }

        // Inflate the layout for this fragment
        return root
    }
}