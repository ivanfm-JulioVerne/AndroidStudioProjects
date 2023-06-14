package com.example.minichef_v1.pantanllas.home.viewPager2.listadoPublicaciones

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Spinner
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.databinding.FragmentListadoPublicacionesBinding
import com.example.minichef_v1.pantanllas.home.viewPager2.listadoPublicaciones.rvPublicaciones.PublicacionesAdapter

class ListadoPublicacionesFragment : Fragment() {

    companion object{
        private const val ARG_OBJECT="object"
    }

    private var _binding: FragmentListadoPublicacionesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var fragmentNumerador:Int=0

    private val listadoPublicacionesViewModel: ListadoPublicacionesViewModel by viewModels {
        ListadoPublicacionesViewModelFactory(
            (activity as MainActivity).usuario.admin
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentListadoPublicacionesBinding.inflate(inflater,container,false)

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val eso=toString()
            Log.d(":::eso",eso)
            if (getInt(ARG_OBJECT)==0){
                listadoPublicacionesViewModel.setLista(emptyList())
                listadoPublicacionesViewModel.buscarSiguiendo(listadoPublicacionesViewModel,(activity as MainActivity).usuario.id_usuario)
                fragmentNumerador=0
            }else if (getInt(ARG_OBJECT)==1){
                listadoPublicacionesViewModel.setLista(emptyList())
                listadoPublicacionesViewModel.buscar50mejores(listadoPublicacionesViewModel)
                fragmentNumerador=1
            }
        }
                //binding.tvPrueba.text= getInt(ARG_OBJECT).toString()
        val root= binding.root
        refreshRecyclerView(listadoPublicacionesViewModel.lista.value ?: emptyList(),root)

        listadoPublicacionesViewModel.lista.observe(viewLifecycleOwner) {
            refreshRecyclerView(it, root)
        }

        val categoria: Spinner =binding.categoriaBuscar

        (activity as MainActivity).viewModel.categoriasTexto.observe(viewLifecycleOwner) {
            categoria.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, it)
        }

        //categoria.adapter= ArrayAdapter.createFromResource(this.requireContext(),R.array.categorias,android.R.layout.simple_spinner_dropdown_item)
        categoria.adapter=
            ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,(activity as MainActivity).categoriasTexto)

        binding.btnBuscarPorCategoria.setOnClickListener {
            val categoriaElegida=categoria.selectedItem.toString()
            val categoriaElegidaIdx=(activity as MainActivity).categoriasTexto.indexOf(categoriaElegida)
            if (fragmentNumerador==0){
                listadoPublicacionesViewModel.buscarSiguiendoCategoria(
                    listadoPublicacionesViewModel,
                    (activity as MainActivity).usuario.id_usuario,
                    (activity as MainActivity).categoriasId[categoriaElegidaIdx])
            }else{
                listadoPublicacionesViewModel.buscarPorCategoria(
                    (activity as MainActivity).categoriasId[categoriaElegidaIdx])
            }

            binding.btnCancelarBusqueda.visibility=View.VISIBLE
        }

        binding.btnCancelarBusqueda.setOnClickListener {
            if (fragmentNumerador==0){
                listadoPublicacionesViewModel.buscarSiguiendo(listadoPublicacionesViewModel,(activity as MainActivity).usuario.id_usuario)
            }else{
                listadoPublicacionesViewModel.buscar50mejores(listadoPublicacionesViewModel)
            }
            binding.btnCancelarBusqueda.visibility=View.GONE
        }

        binding.btnBuscarUsuarios.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_listadoUsuariosFragment)
        }

        var busqueda=false

        binding.svBuscarPublicacion.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null && query!="" && fragmentNumerador==1){
                    listadoPublicacionesViewModel.buscarPorTitulo(query)
                    busqueda=true
                }else if (query!=null && query!="" && fragmentNumerador==0){
                    listadoPublicacionesViewModel.buscarSiguiendoTitulo(listadoPublicacionesViewModel,(activity as MainActivity).usuario.id_usuario,query)
                    busqueda=true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean{
                if ((newText==null || newText == "") && fragmentNumerador==1 && busqueda){
                    listadoPublicacionesViewModel.buscar50mejores(listadoPublicacionesViewModel)
                    busqueda=false
                }else if ((newText==null || newText == "") && fragmentNumerador==0 && busqueda){
                    listadoPublicacionesViewModel.buscarSiguiendo(listadoPublicacionesViewModel,(activity as MainActivity).usuario.id_usuario)
                    busqueda=false
                }
                return false
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun refreshRecyclerView(publicaciones:List<Publicacion>, view: View){
        val recyclerView=view.findViewById<RecyclerView>(R.id.rv_publicaciones)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        recyclerView.adapter= PublicacionesAdapter(publicaciones)
    }
}