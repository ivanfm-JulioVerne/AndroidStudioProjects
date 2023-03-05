package com.example.repaso_examen_rec.ui.alimentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.repaso_examen_rec.databinding.FragmentAlimentosBinding;
import com.example.repaso_examen_rec.ui.alimentos.anadir_alimento.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AlimentosFragment extends Fragment {

    private FragmentAlimentosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AlimentosViewModel alimentosViewModel =
                new ViewModelProvider(this).get(AlimentosViewModel.class);

        binding = FragmentAlimentosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ViewPager2 viewPager2=binding.pager;
        TabLayout tabs=binding.tabLayout;
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(this.getActivity());
        viewPager2.setAdapter(pagerAdapter);
        TabLayoutMediator  tabLayoutMediator=new TabLayoutMediator(tabs,viewPager2,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Ver Alimentos");
                            break;
                        case 1:
                            tab.setText("Añadir Alimento");
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