package com.example.minichef_v1.pantanllas.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.databinding.FragmentHomeBinding
import com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.AutorViewModel
import com.example.minichef_v1.pantanllas.home.detallePublicacion.autor.AutorViewModelFactory
import com.example.minichef_v1.pantanllas.home.rvPublicaciones.PublicacionesAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(
            (activity as MainActivity).usuario.admin
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        refreshRecyclerView(homeViewModel.lista.value ?: emptyList(),root)

        homeViewModel.lista.observe(viewLifecycleOwner, Observer {
            refreshRecyclerView(it,root)
        })

        val categoria: Spinner =binding.categoriaBuscar
        categoria.adapter= ArrayAdapter.createFromResource(this.requireContext(),R.array.categorias,android.R.layout.simple_spinner_dropdown_item)

        binding.btnBuscarPorCategoria.setOnClickListener {
            homeViewModel.buscarPorCategoria(binding.categoriaBuscar.selectedItemId.toString())
        }

        binding.svBuscarPublicacion.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null && query!=""){
                    homeViewModel.buscarPorTitulo(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean{
                if (newText==null || newText == ""){
                    homeViewModel.buscar50mejores(homeViewModel)
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

    private fun refreshRecyclerView(publicaciones:List<Publicacion>,view: View){
        val recyclerView=view.findViewById<RecyclerView>(R.id.rv_publicaciones)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        recyclerView.adapter=PublicacionesAdapter(publicaciones)
    }
}