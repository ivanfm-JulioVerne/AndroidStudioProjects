package com.example.repaso_examen_rec.ui.alimentos.anadir_alimento;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.repaso_examen_rec.ui.alimentos.ver_alimentos.VerAlimentosFragment;

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
                return new VerAlimentosFragment();
            case 1:
                return new AnadirAlimentoFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
