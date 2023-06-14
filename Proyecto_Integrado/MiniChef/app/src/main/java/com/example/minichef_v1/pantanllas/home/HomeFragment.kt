package com.example.minichef_v1.pantanllas.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.minichef_v1.R
import com.example.minichef_v1.databinding.FragmentHomeBinding
import com.example.minichef_v1.pantanllas.home.viewPager2.ViewPager2Adapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ViewPager2Adapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adapter=ViewPager2Adapter(this)

        binding.pager.adapter=adapter
        val tabLayoutMediator=TabLayoutMediator(binding.tabLayout,binding.pager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = resources.getText(R.string.siguiendo)
                }
                1 -> {
                    tab.text = resources.getText(R.string.populares)
                }
            }
        }
        tabLayoutMediator.attach()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}