package com.example.repasorecyclerviewtablayout.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.repasorecyclerviewtablayout.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ViewPager2 viewPager2=binding.pager;
        TabLayout tabLayout=binding.tabLayout;
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(this.getActivity());
        viewPager2.setAdapter(pagerAdapter);
        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout,viewPager2,
                (tab,position)->{
                    switch (position) {
                        case 0:
                            tab.setText("Ver Pilotos");
                            break;
                        case 1:
                            tab.setText("Ver Circuitos");
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