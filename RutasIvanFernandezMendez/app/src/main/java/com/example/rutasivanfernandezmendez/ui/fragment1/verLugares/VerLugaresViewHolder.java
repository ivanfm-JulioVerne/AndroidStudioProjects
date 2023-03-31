package com.example.rutasivanfernandezmendez.ui.fragment1.verLugares;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rutasivanfernandezmendez.R;
import com.example.rutasivanfernandezmendez.modelo.Lugar;

public class VerLugaresViewHolder extends RecyclerView.ViewHolder {

    private View view;

    public VerLugaresViewHolder(@NonNull View itemView) {
        super(itemView);
        view=itemView;
    }

    public void render(Lugar lugar){
        TextView nombre=(TextView) view.findViewById(R.id.nombreLugar);
        TextView kms=(TextView) view.findViewById(R.id.kmsLugar);

        nombre.setText(lugar.getNombre());
        kms.setText(lugar.getKms()+"");
    }
}
