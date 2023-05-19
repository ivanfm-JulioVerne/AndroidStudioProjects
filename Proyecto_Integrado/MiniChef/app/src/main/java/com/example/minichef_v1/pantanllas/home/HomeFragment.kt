package com.example.minichef_v1.pantanllas.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.databinding.FragmentHomeBinding
import com.example.minichef_v1.pantanllas.home.rvPublicaciones.PublicacionesAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        refreshRecyclerView(homeViewModel.lista.value ?: emptyList(),root)

        homeViewModel.lista.observe(viewLifecycleOwner, Observer {
            refreshRecyclerView(it,root)
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