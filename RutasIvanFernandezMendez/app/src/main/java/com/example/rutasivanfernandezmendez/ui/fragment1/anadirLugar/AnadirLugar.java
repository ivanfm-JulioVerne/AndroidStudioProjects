package com.example.rutasivanfernandezmendez.ui.fragment1.anadirLugar;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rutasivanfernandezmendez.R;
import com.example.rutasivanfernandezmendez.databinding.Fragment1Binding;
import com.example.rutasivanfernandezmendez.databinding.Fragment2Binding;
import com.example.rutasivanfernandezmendez.databinding.FragmentAnadirLugarBinding;
import com.example.rutasivanfernandezmendez.modelo.Lugar;

public class AnadirLugar extends Fragment {

    private FragmentAnadirLugarBinding binding;
    private AnadirLugarListener lugarListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentAnadirLugarBinding.inflate(inflater,container,false);
        View root=binding.getRoot();

        Button anadir=binding.bAnadir;
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nombre=binding.nombreLugarEditText;
                TextView kms=binding.kmsLugarEditText;

                Lugar lugar= new Lugar(nombre.getText()+"",new Float(kms.getText()+""));
                lugarListener.anadirLugar(lugar);

                nombre.setText("");
                kms.setText("");
            }
        });

        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            lugarListener = (AnadirLugarListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " Se debe implementar anadirLugarListener");
        }
    }
}