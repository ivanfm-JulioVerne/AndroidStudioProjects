package com.example.minichef_v1.pantanllas.perfil

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.databinding.FragmentPerfilBinding
import com.example.minichef_v1.pantanllas.perfil.rvPublicaciones.PublicacionesAdapter
import com.google.firebase.auth.FirebaseAuth

class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val perfilViewModel:PerfilViewModel by viewModels { PerfilViewModelFactory((activity as MainActivity).usuario) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val idUsaurio=FirebaseAuth.getInstance().currentUser?.uid
        Log.d(":::Perfil",idUsaurio ?: "")
        val usuario=(activity as MainActivity).usuario

        binding.btnEditar.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_perfil_to_editarFragment)
        }

        binding.tvNickname.text=usuario.nickname
        binding.tvNombre.text=usuario.nombre
        binding.tvSeguidores.text="Seguidores\n" + usuario.num_seguidores.toString()
        binding.tvPublicacion.text="Publiciaciones\n" + usuario.num_publicacion.toString()
        binding.tvSiguiendo.text="Siguiendo\n" + usuario.num_siguiendo.toString()
        binding.tvBiografia.text=usuario.biografia

        perfilViewModel.lista.observe(viewLifecycleOwner, Observer {
            refreshRecyclerView(it,root)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun refreshRecyclerView(publicaciones:List<Publicacion>, view: View){
        val recyclerView=view.findViewById<RecyclerView>(R.id.rv_publicacionesPerfilPropio)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        recyclerView.adapter= PublicacionesAdapter(publicaciones)
    }
}