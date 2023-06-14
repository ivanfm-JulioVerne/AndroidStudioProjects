package com.example.minichef_v1.pantanllas.home.viewPager2

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.minichef_v1.pantanllas.home.viewPager2.listadoPublicaciones.ListadoPublicacionesFragment

class ViewPager2Adapter(f:Fragment) : FragmentStateAdapter(f) {

    companion object{
        private const val ARG_OBJECT="object"
    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment= ListadoPublicacionesFragment()
        fragment.arguments= Bundle().apply {
            putInt(ARG_OBJECT, position)
        }
        return fragment
    }
}