package com.example.minichef_v1.pantanllas.home.detallePublicacion.autor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.bd.modelo.Usuario
import com.example.minichef_v1.databinding.FragmentAutorBinding
import com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.rvPublicaciones.PublicacionesAutorAdapter

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentAutorBinding.inflate(inflater,container,false)

        val root=binding.root

        if ((activity as MainActivity).usuario.admin){
            binding.btnBorrarAutor.visibility=View.VISIBLE
        }else{
            binding.btnBorrarAutor.visibility=View.GONE
        }

        val bundle=requireArguments()
        val autor=Usuario(
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
            binding.tvBaneadoAutor.visibility=View.VISIBLE
            //binding.btnBorrarAutor.text="Desbanear"
            binding.btnBorrarAutor.text=resources.getText(R.string.desbanear)
        }else{
            binding.tvBaneadoAutor.visibility=View.GONE
            //binding.btnBorrarAutor.text="Banear"
            binding.btnBorrarAutor.text=resources.getText(R.string.banear)
        }

        binding.btnBorrarAutor.setOnClickListener {
            if (autor.baneado){
                autor.baneado=false
                //binding.btnBorrarAutor.text="Banear"
                binding.btnBorrarAutor.text=resources.getText(R.string.banear)
                binding.tvBaneadoAutor.visibility=View.GONE
                autorViewModel.unbanearAutor(autor.id_usuario)
            }else{
                autor.baneado=true
                //binding.btnBorrarAutor.text="Desbanear"
                binding.btnBorrarAutor.text=resources.getText(R.string.desbanear)
                binding.tvBaneadoAutor.visibility=View.VISIBLE
                autorViewModel.banearAutor(autor.id_usuario)
            }
        }

        binding.tvNicknameAutor.text=autor.nickname
        binding.tvNombreAutor.text=autor.nombre
        binding.tvBiografiaAutor.text=autor.biografia

        val seguidores=(resources.getText(R.string.seguirdores) as String)+"\n" + autor.num_seguidores
        val siguiendo=(resources.getText(R.string.siguiendo) as String)+"\n" + autor.num_siguiendo
        val publicaciones=(resources.getText(R.string.publicaciones) as String)+"\n" + autor.num_publicacion

        binding.tvSeguidoresAutor.text=seguidores
        binding.tvSiguiendoAutor.text=siguiendo
        binding.tvPublicacionAutor.text=publicaciones

        refreshRecyclerView(emptyList(),root)

        autorViewModel.lista.observe(viewLifecycleOwner) {
            refreshRecyclerView(it, root)
        }
        autorViewModel.leSigue.observe(viewLifecycleOwner) {
            if (it) {
                //binding.btnSeguir.text = "Dejar de Seguir"
                binding.btnSeguir.text = resources.getText(R.string.dejarSeguir)
            } else {
                //binding.btnSeguir.text = "Seguir"
                binding.btnSeguir.text = resources.getText(R.string.seguir)
            }
        }

        binding.btnSeguir.setOnClickListener {
            if (autorViewModel.leSigue.value!!){
                autorViewModel.dejarSeguir()
                autor.num_seguidores=autor.num_seguidores-1
                val seguirdores=resources.getString(R.string.seguirdores)+"\n" + autor.num_seguidores
                binding.tvSeguidoresAutor.text=seguirdores
            }else{
                autorViewModel.seguir()
                autor.num_seguidores=autor.num_seguidores+1
                val seguirdores=resources.getString(R.string.seguirdores)+"\n" + autor.num_seguidores
                binding.tvSeguidoresAutor.text=seguirdores
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