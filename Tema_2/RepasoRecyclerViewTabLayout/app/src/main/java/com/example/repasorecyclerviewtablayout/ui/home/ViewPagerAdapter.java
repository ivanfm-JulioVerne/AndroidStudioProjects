package com.example.repasorecyclerviewtablayout.ui.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.repasorecyclerviewtablayout.ui.home.ver_circuitos.VerCircuitos;
import com.example.repasorecyclerviewtablayout.ui.home.ver_pilotos.VerPilotos;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new VerPilotos();
            case 1:
                return new VerCircuitos();
        }
        return null;    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
