package com.example.repaso_examen_rec.ui.alimentos.ver_alimentos;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repaso_examen_rec.R;
import com.example.repaso_examen_rec.modelo.Alimento;

public class VerAlimentosViewHolder  extends RecyclerView.ViewHolder {

    private View view;

    public VerAlimentosViewHolder(@NonNull View itemView) {
        super(itemView);
        view=itemView;
    }

    public void render(Alimento alimento){
        TextView nombre=(TextView) view.findViewById(R.id.nombreAlimento);
        TextView kCal=(TextView) view.findViewById(R.id.kCalAlimento);

        nombre.setText(alimento.getNombre());
        kCal.setText(alimento.getNumKcal()+"");
    }
}
