package com.example.minichef_v1.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.minichef_v1.AuthActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.dao.DAOUsuario
import com.example.minichef_v1.bd.dao.IDAOUsuario
import com.example.minichef_v1.databinding.FragmentNoAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

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
        val bGoogle:Button=binding.bLoginGoogle
        bGoogle.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient= GoogleSignIn.getClient(requireActivity(),googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, 100)
        }

        // Inflate the layout for this fragment
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100){
            val task= GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account=task.getResult(ApiException::class.java)
                if (account != null){
                    val credential=GoogleAuthProvider.getCredential(account.idToken,null)

                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                        if (it.isSuccessful){
                            val daoUsuario: IDAOUsuario = DAOUsuario()
                            daoUsuario.comprobarUsuarioExiste(it.result.user?.uid.toString(),binding.root)
                            //(activity as AuthActivity).goToMainActivity()
                        } else {
                        }
                    }
                }
            } catch (e: ApiException){
                Log.d(":::Error ApiException", e.toString()+" - "+e.message.toString())
            }
        }
    }
}