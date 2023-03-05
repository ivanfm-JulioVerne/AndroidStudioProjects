package com.example.repaso_examen_rec.ui.alimentos.ver_alimentos;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repaso_examen_rec.R;
import com.example.repaso_examen_rec.modelo.Alimento;

import java.util.ArrayList;

public class VerAlimentosAdapter extends RecyclerView.Adapter<VerAlimentosViewHolder> {

    private ArrayList<Alimento> alimentos;

    public VerAlimentosAdapter(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

    @NonNull
    @Override
    public VerAlimentosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        return new VerAlimentosViewHolder(layoutInflater.inflate(R.layout.item_alimento,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VerAlimentosViewHolder holder, int position) {
        Alimento alimento=alimentos.get(position);
        holder.render(alimento);
    }

    @Override
    public int getItemCount() {
        return alimentos.size();
    }
}
