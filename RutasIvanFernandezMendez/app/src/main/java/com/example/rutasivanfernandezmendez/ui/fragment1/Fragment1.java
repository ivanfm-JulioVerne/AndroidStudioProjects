package com.example.rutasivanfernandezmendez.ui.fragment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.rutasivanfernandezmendez.databinding.Fragment1Binding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Fragment1 extends Fragment {

    private Fragment1Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Fragment1ViewModel fragment1ViewModel =
                new ViewModelProvider(this).get(Fragment1ViewModel.class);

        binding = Fragment1Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ViewPager2 viewPager2=binding.pager;
        TabLayout tabs=binding.tabLayout;
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(this.getActivity());
        viewPager2.setAdapter(pagerAdapter);
        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabs,viewPager2,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Ver Lugares");
                            break;
                        case 1:
                            tab.setText("Añadir Lugar");
                            break;
                    }
                });
        tabLayoutMediator.attach();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}