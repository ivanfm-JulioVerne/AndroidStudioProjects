package com.example.minichef_v1.pantanllas.perfil

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.bd.dao.DAOUsuario
import com.example.minichef_v1.bd.dao.IDAOUsuario
import com.example.minichef_v1.bd.modelo.Usuario
import com.example.minichef_v1.databinding.FragmentPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val daoUsuario:IDAOUsuario=DAOUsuario()

        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val idUsaurio=FirebaseAuth.getInstance().currentUser?.uid
        Log.d(":::Perfil",idUsaurio ?: "")
        val usuario=(activity as MainActivity).usuario

        binding.tvNickname.text=usuario.nickname
        binding.tvSeguidores.text="Seguidores\n" + usuario.num_seguidores.toString()
        binding.tvPublicacion.text="Publiciaciones\n" + usuario.num_publicacion.toString()
        binding.tvSiguiendo.text="Siguiendo\n" + usuario.num_siguiendo.toString()
        binding.tvBiografia.text=usuario.biografia

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}