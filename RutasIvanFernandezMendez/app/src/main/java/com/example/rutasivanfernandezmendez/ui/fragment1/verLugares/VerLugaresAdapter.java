package com.example.rutasivanfernandezmendez.ui.fragment1.verLugares;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rutasivanfernandezmendez.R;
import com.example.rutasivanfernandezmendez.modelo.Lugar;

import java.util.ArrayList;

public class VerLugaresAdapter extends RecyclerView.Adapter<VerLugaresViewHolder> {

    private ArrayList<Lugar> lugares;

    public VerLugaresAdapter(ArrayList<Lugar> lugares) {
        this.lugares = lugares;
    }

    @NonNull
    @Override
    public VerLugaresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        return new VerLugaresViewHolder(layoutInflater.inflate(R.layout.item_lugar,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VerLugaresViewHolder holder, int position) {
        Lugar lugar=lugares.get(position);
        holder.render(lugar);
    }

    @Override
    public int getItemCount() {
        return lugares.size();
    }
}
