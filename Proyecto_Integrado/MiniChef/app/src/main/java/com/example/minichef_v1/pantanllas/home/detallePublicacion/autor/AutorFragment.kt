package com.example.minichef_v1.pantanllas.home.detallePublicacion.autor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.bd.modelo.Usuario
import com.example.minichef_v1.databinding.FragmentAutorBinding
import com.example.minichef_v1.pantanllas.home.detallePublicacion.DetallePublicacionViewModel
import com.example.minichef_v1.pantanllas.home.detallePublicacion.DetallePublicacionViewModelFactory
import com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.rvPublicaciones.PublicacionesAutorAdapter
import com.example.minichef_v1.pantanllas.home.rvPublicaciones.PublicacionesAdapter

class AutorFragment : Fragment() {

    private var _binding: FragmentAutorBinding?=null
    private val binding get() = _binding!!

    private val autorViewModel: AutorViewModel by viewModels {
        AutorViewModelFactory(
            requireArguments().getString("idAutor")!!,
            (activity as MainActivity).usuario.id_usuario,
            (activity as MainActivity).usuario.admin
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentAutorBinding.inflate(inflater,container,false)

        val root=binding.root

        if ((activity as MainActivity).usuario.admin){
            binding.btnBorrarAutor.visibility=View.VISIBLE
        }else{
            binding.btnBorrarAutor.visibility=View.GONE
        }

        val bundle=requireArguments()
        val autor:Usuario=Usuario(
            bundle.getString("idAutor")!!,
            bundle.getString("nickname")!!,
            bundle.getString("nombre")!!,
            bundle.getString("biografia"),
            bundle.getBoolean("admin"),
            bundle.getBoolean("baneado"),
            bundle.getLong("num_seguidores"),
            bundle.getLong("num_Siguiendo"),
            bundle.getLong("num_publicacion")
        )

        if (autor.baneado){
            binding.btnBorrarAutor.text="Desbanear"
        }else{
            binding.btnBorrarAutor.text="Banear"
        }

        binding.btnBorrarAutor.setOnClickListener {
            if (autor.baneado){
                autorViewModel.unbanearAutor(autor.id_usuario)
            }else{
                autorViewModel.banearAutor(autor.id_usuario)
            }
        }

        binding.tvNicknameAutor.text=autor.nickname
        binding.tvNombreAutor.text=autor.nombre
        binding.tvBiografiaAutor.text=autor.biografia
        binding.tvSeguidoresAutor.text="Seguidores\n" + autor.num_seguidores
        binding.tvSiguiendoAutor.text="Siguiendo\n" + autor.num_siguiendo
        binding.tvPublicacionAutor.text="Publiciaciones\n" + autor.num_publicacion

        refreshRecyclerView(emptyList(),root)

        autorViewModel.lista.observe(viewLifecycleOwner, Observer {
            refreshRecyclerView(it, root)
        })
        autorViewModel.leSigue.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.btnSeguir.text="Dejar de Seguir"
            }else{
                binding.btnSeguir.text="Seguir"
            }
        })

        binding.btnSeguir.setOnClickListener {
            if (autorViewModel.leSigue.value!!){
                autorViewModel.dejarSeguir()
                autor.num_seguidores=autor.num_seguidores-1
                binding.tvSeguidoresAutor.text="Seguidores\n" + autor.num_seguidores
            }else{
                autorViewModel.seguir()
                autor.num_seguidores=autor.num_seguidores+1
                binding.tvSeguidoresAutor.text="Seguidores\n" + autor.num_seguidores
            }
        }

        // Inflate the layout for this fragment
        return root
    }

    private fun refreshRecyclerView(publicaciones:List<Publicacion>, view: View){
        val recyclerView=view.findViewById<RecyclerView>(R.id.rv_publicacionesPerfilAutor)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        recyclerView.adapter= PublicacionesAutorAdapter(publicaciones)
    }
}