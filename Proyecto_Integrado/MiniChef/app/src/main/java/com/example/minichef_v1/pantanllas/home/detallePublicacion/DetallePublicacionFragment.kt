package com.example.minichef_v1.pantanllas.home.detallePublicacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Comentario
import com.example.minichef_v1.bd.modelo.Usuario
import com.example.minichef_v1.databinding.FragmentDetallePublicacionBinding
import com.example.minichef_v1.pantanllas.home.detallePublicacion.rvComentarios.ComentariosAdapter
import com.example.minichef_v1.pantanllas.home.detallePublicacion.rvIngredientes.IngredientesAdapter
import com.example.minichef_v1.pantanllas.home.detallePublicacion.rvPasos.PasosAdapter
import com.example.minichef_v1.pantanllas.perfil.PerfilViewModel
import com.example.minichef_v1.pantanllas.perfil.PerfilViewModelFactory
import java.lang.Integer.parseInt

class DetallePublicacionFragment : Fragment() {

    private var _binding: FragmentDetallePublicacionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var autor:Usuario

    private val detallePublicacionViewModel: DetallePublicacionViewModel by viewModels { DetallePublicacionViewModelFactory((activity as MainActivity).usuario.id_usuario,
        (activity as MainActivity).publicacionSeleccionada.id_publicacion!!,
        (activity as MainActivity).publicacionSeleccionada.id_usuario!!,
        (activity as MainActivity).usuario.admin!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detallePublicacionViewModel.buscarAutor((activity as MainActivity).publicacionSeleccionada.id_usuario!!)

        _binding = FragmentDetallePublicacionBinding.inflate(inflater, container, false)
        val root=binding.root

        if ((activity as MainActivity).usuario.admin){
            binding.btnBorrarDetallePublicacion.visibility=View.VISIBLE
        }else{
            binding.btnBorrarDetallePublicacion.visibility=View.GONE
        }

        //detallePublicacionViewModel.comprobarMeGusta((activity as MainActivity).usuario.id_usuario,publicacion.id_publicacion ?:"")
        //detallePublicacionViewModel.mostrarComentarios(publicacion.id_publicacion ?:"")
        //detallePublicacionViewModel.buscarAutor(publicacion.id_publicacion!!)

        val publicacion=(activity as MainActivity).publicacionSeleccionada
        val categorias=resources.getStringArray(R.array.categorias)

        if (publicacion.baneado){
            binding.tvBaneadoDetallePublicacion.visibility=View.VISIBLE
            binding.btnBorrarDetallePublicacion.text="Desbanear"
        }else{
            binding.tvBaneadoDetallePublicacion.visibility=View.GONE
            binding.btnBorrarDetallePublicacion.text="Banear"
        }

        binding.btnBorrarDetallePublicacion.setOnClickListener {
            if (publicacion.baneado){
                detallePublicacionViewModel.unbanearPublicacion(publicacion.id_publicacion!!)
                binding.tvBaneadoDetallePublicacion.visibility=View.VISIBLE
                binding.btnBorrarDetallePublicacion.text="Desbanear"
            }else{
                detallePublicacionViewModel.banearPublicacion(publicacion.id_publicacion!!)
                binding.tvBaneadoDetallePublicacion.visibility=View.GONE
                binding.btnBorrarDetallePublicacion.text="Banear"
            }
        }

        binding.tituloDetallePublicacion.text=publicacion.titulo
        binding.descripcionDetallePublicacion.text=publicacion.descripcion
        binding.categoria1DetallePublicacion.text=categorias[parseInt(publicacion.id_categoria?.get(0) ?: "0")]
        binding.categoria2DetallePublicacion.text=categorias[parseInt(publicacion.id_categoria?.get(1) ?: "0")]
        binding.meGustaDetallePublicacion.text=""+publicacion.num_likes
        Glide.with(requireContext()).load(publicacion.imagen!!).into(binding.imagenDetallePublicacion)
        refreshRvIngrediente(publicacion.ingredientes!!,root)
        refreshRvPasos(publicacion.pasos!!,root)
        refreshRvComentarios(emptyList(),root)

        detallePublicacionViewModel.meGusto.observe(viewLifecycleOwner, Observer {
            if (!it){
                binding.btnLike.text="Me Gusta"
            }else{
                binding.btnLike.text="Quitar Me Gusta"
            }
        })
        detallePublicacionViewModel.comentarios.observe(viewLifecycleOwner, Observer {
            refreshRvComentarios(it,root)
        })
        detallePublicacionViewModel.autor.observe(viewLifecycleOwner, Observer {
            binding.tvNicknameAutorPublicacion.text=it.nickname
            autor=it
        })

        binding.btnLike.setOnClickListener {
            if (detallePublicacionViewModel.meGusto.value!!){
                publicacion.num_likes=publicacion.num_likes!!-1
                binding.meGustaDetallePublicacion.text=""+publicacion.num_likes
                detallePublicacionViewModel.pulsaMeGusta((activity as MainActivity).usuario.id_usuario, publicacion.id_publicacion ?:"")
            }else{
                publicacion.num_likes=publicacion.num_likes!!+1
                binding.meGustaDetallePublicacion.text=""+publicacion.num_likes
                detallePublicacionViewModel.pulsaMeGusta((activity as MainActivity).usuario.id_usuario, publicacion.id_publicacion ?:"")
            }
        }
        binding.btnEnviarComentario.setOnClickListener {
            if (binding.etComentario.text.isNotEmpty()){
                detallePublicacionViewModel.anadirComentario(
                    publicacion.id_publicacion!!,
                    (activity as MainActivity).usuario.id_usuario,
                    binding.etComentario.text.toString(),
                    (activity as MainActivity).usuario.nickname
                )
                binding.etComentario.text.clear()
            }
        }
        binding.tvNicknameAutorPublicacion.setOnClickListener{
            if (!detallePublicacionViewModel.autorIsUsuario()) {
                val bundle = Bundle()
                bundle.putString("idAutor", autor.id_usuario)
                bundle.putString("nickname", autor.nickname)
                bundle.putString("nombre", autor.nombre)
                bundle.putString("biografia", autor.biografia)
                bundle.putBoolean("admin", autor.admin)
                bundle.putBoolean("baneado", autor.baneado)
                bundle.putLong("num_seguidores", autor.num_seguidores)
                bundle.putLong("num_Siguiendo", autor.num_siguiendo)
                bundle.putLong("num_publicacion", autor.num_publicacion)
                root.findNavController()
                    .navigate(R.id.action_detallePublicacionFragment_to_autorFragment, bundle)
            }else{
                root.findNavController().navigate(R.id.action_detallePublicacionFragment_to_navigation_perfil)
            }
        }

        return root
    }

    private fun refreshRvIngrediente(ingredientes:ArrayList<String>, view: View){
        val recyclerView=view.findViewById<RecyclerView>(R.id.rv_ingrediente)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        recyclerView.adapter= IngredientesAdapter(ingredientes)
    }

    private fun refreshRvPasos(pasos:ArrayList<String>,view: View){
        val recyclerView=view.findViewById<RecyclerView>(R.id.rv_pasos)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        recyclerView.adapter=PasosAdapter(pasos)
    }

    private fun refreshRvComentarios(comentarios:List<Comentario>,view: View){
        val recyclerView=view.findViewById<RecyclerView>(R.id.rv_comentarios)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        recyclerView.adapter= ComentariosAdapter(comentarios,(activity as MainActivity),detallePublicacionViewModel)
    }
}