package com.example.minichef_v1.pantanllas.home.listadoUsuarios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.bd.modelo.Usuario
import com.example.minichef_v1.databinding.FragmentListadoUsuariosBinding
import com.example.minichef_v1.pantanllas.home.listadoUsuarios.rvUsuarios.UsuariosAdapter

class ListadoUsuariosFragment : Fragment() {

    private var _binding: FragmentListadoUsuariosBinding? = null
    private val binding get() = _binding!!

    private val listadoUsuariosViewModel: ListadoUsuariosViewModel by viewModels {
        ListadoUsuariosViewModelFactory(
            (activity as MainActivity).usuario.admin
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentListadoUsuariosBinding.inflate(inflater,container,false)
        val root=binding.root

        refreshRecyclerView(listadoUsuariosViewModel.lista.value ?: emptyList())

        listadoUsuariosViewModel.lista.observe(viewLifecycleOwner) {
            refreshRecyclerView(it)
        }

        binding.svBuscarUsuarios.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null && query!=""){
                    listadoUsuariosViewModel.buscarUsuarioPorNickname(query)
                    //listadoUsuariosViewModel.buscarPorTitulo(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean{
                if (newText==null || newText == ""){
                    listadoUsuariosViewModel.buscarUsuariosPopulares()
                }
                return false
            }
        })

        return root
    }

    private fun refreshRecyclerView(usuarios:List<Usuario>){
        val recyclerView=binding.rvUsuarios
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        recyclerView.adapter= UsuariosAdapter(usuarios)
    }
}