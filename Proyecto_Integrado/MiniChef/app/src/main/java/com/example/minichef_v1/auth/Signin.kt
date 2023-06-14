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
import com.example.minichef_v1.AuthActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.dao.usuario.DAOUsuario
import com.example.minichef_v1.bd.dao.usuario.IDAOUsuario
import com.example.minichef_v1.databinding.FragmentSigninBinding
import com.example.minichef_v1.bd.modelo.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

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
    ): View {

        _binding= FragmentSigninBinding.inflate(inflater,container,false)
        val root=binding.root

        val bLogin: Button =binding.bLoginSignin
        bLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signin_to_login)
        }
        val bRegistro: Button = binding.bRegSignin
        bRegistro.setOnClickListener{
            //Log.d(":::Contraseña",binding.editTextTextPassword3.text.toString())
            //Log.d(":::Contraseña 2 ",binding.editTextTextPassword4.text.toString())
            if (binding.editTextEmailReg.text.isNotEmpty() &&
                binding.editTextPasswordReg.text.isNotEmpty() &&
                binding.editTextPasswordReg2.text.isNotEmpty() &&
                binding.editTextNickname.text.isNotEmpty() &&
                binding.editTextNombre.text.isNotEmpty()

            ){
                if (binding.editTextPasswordReg.text.toString() != binding.editTextPasswordReg2.text.toString()){
                    alertContrasenaNoCoinciden()
                }else{
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                            binding.editTextEmailReg.text.toString(),
                            binding.editTextPasswordReg.text.toString())
                        .addOnCompleteListener{
                            if (it.isSuccessful){
                                //showAlert()
                                Log.d(":::Nuevo Usuario",it.result.user?.uid ?: "")
                                val daoUsuario: IDAOUsuario = DAOUsuario()
                                val usuario = Usuario(it.result.user!!.uid,
                                    binding.editTextNickname.text.toString(),
                                    binding.editTextNombre.text.toString(),
                                    binding.editTextBiografia.text.toString()
                                )
                                daoUsuario.crearUsuarioNoAdmin(usuario)
                                (activity as AuthActivity).goToMainActivity(usuario)
                                //val prefs:SharedPreferences
                            }else{
                                if (it.exception is FirebaseAuthUserCollisionException){
                                    alertUsuarioExistente()
                                }else if(it.exception is FirebaseAuthInvalidCredentialsException){
                                    alertMalEmail()
                                }else{
                                    Log.e(":::Error",it.exception.toString())
                                    showAlert()
                                }
                            }
                        }
                }
            }else{
                showAlertFaltanDatos()
            }
        }

        // Inflate the layout for this fragment
        return root
    }

    private fun showAlertFaltanDatos(){
        AlertDialog.Builder(activity)
            .setTitle("Error")
            .setMessage("No se pueden dejar en blanco los campos email, contraseña, confirmar contraseña, nickname y nombre")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }

    private fun showAlert(){
        AlertDialog.Builder(activity)
            .setTitle("Error")
            .setMessage("Se ha producido un error registrando al usuario")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }

    private fun alertUsuarioExistente(){
        AlertDialog.Builder(activity)
            .setTitle("Error")
            .setMessage("El usuario ya existe")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }

    private fun alertMalEmail(){
        AlertDialog.Builder(activity)
            .setTitle("Error")
            .setMessage("El email esta mal formado")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }

    private fun alertContrasenaNoCoinciden(){
        AlertDialog.Builder(activity)
            .setTitle("Error")
            .setMessage("Las contraseñas no coinciden")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }
}