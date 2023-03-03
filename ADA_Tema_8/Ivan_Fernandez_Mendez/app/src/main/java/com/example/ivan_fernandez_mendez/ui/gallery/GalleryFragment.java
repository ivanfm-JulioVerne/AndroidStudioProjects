package com.example.ivan_fernandez_mendez.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ivan_fernandez_mendez.R;
import com.example.ivan_fernandez_mendez.dao.DaoRuta;
import com.example.ivan_fernandez_mendez.databinding.FragmentGalleryBinding;
import com.example.ivan_fernandez_mendez.modelo.Ruta;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button anadir=(Button)root.findViewById(R.id.anadir);
        DaoRuta dao=new DaoRuta("rutasIvanFernandezMendez");
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ruta ruta=new Ruta(galleryViewModel.longIni().getValue(),galleryViewModel.latIni().getValue(),
                        galleryViewModel.rumbo().getValue(),galleryViewModel.distancia().getValue());
                dao.addRuta(ruta);
                Log.d("::::Insert completado","");
            }
        });
        Button ver=(Button)root.findViewById(R.id.anadir);
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.getRutas();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}