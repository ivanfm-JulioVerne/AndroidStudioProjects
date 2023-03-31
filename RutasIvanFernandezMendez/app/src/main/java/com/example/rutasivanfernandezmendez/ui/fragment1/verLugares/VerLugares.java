package com.example.rutasivanfernandezmendez.ui.fragment1.verLugares;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rutasivanfernandezmendez.MainActivity;
import com.example.rutasivanfernandezmendez.R;
import com.example.rutasivanfernandezmendez.databinding.Fragment1Binding;
import com.example.rutasivanfernandezmendez.databinding.FragmentVerLugaresBinding;

public class VerLugares extends Fragment {

    private FragmentVerLugaresBinding binding;
    private BorrarLugar borrarLugar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentVerLugaresBinding.inflate(inflater,container,false);
        View root=binding.getRoot();

        RecyclerView recyclerView=(RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(new VerLugaresAdapter(((MainActivity)this.getActivity()).getLugares()));

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener
                (root.getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                borrarLugar.borrarLugar(position);
                recyclerView.setAdapter(new VerLugaresAdapter(((MainActivity)view.getContext()).getLugares()));
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            borrarLugar = (BorrarLugar) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " Se debe implementar borrarAlimento");
        }

    }
}