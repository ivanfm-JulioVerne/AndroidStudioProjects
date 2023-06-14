package com.example.minichef_v1.auth

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.dao.usuario.DAOUsuario
import com.example.minichef_v1.bd.dao.usuario.IDAOUsuario
import com.example.minichef_v1.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : Fragment() {

    private var _binding:FragmentLoginBinding?=null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding= FragmentLoginBinding.inflate(inflater,container,false)
        val root:View=binding.root

        val bRegistro: Button = binding.bLoginSignin
        bRegistro.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signin)
        }
        val bLogin:Button= binding.bLoginLogin
        bLogin.setOnClickListener {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.editTextEmailLogin.text.toString(),
                binding.editTextPasswordLogin.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d(":::Conectado", it.result.user?.uid.toString())
                    val daoUsuario: IDAOUsuario = DAOUsuario()
                    daoUsuario.getUsuarioByIdFromLogin(it.result.user?.uid ?: "",root)
                    //(activity as AuthActivity).goToMainActivity()
                } else {
                    Log.d(":::Desconectado", "")
                }
            }.addOnFailureListener{
                alertNoExiste()
            }
        }

        // Inflate the layout for this fragment
        return root
    }

    private fun alertNoExiste(){
        AlertDialog.Builder(activity)
            .setMessage("Usuario o contraseña incorrecta")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }
}