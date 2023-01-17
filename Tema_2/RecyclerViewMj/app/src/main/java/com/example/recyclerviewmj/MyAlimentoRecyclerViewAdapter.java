package com.example.recyclerviewmj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewmj.modelo.Alimento;

import java.util.ArrayList;

public class MyAlimentoRecyclerViewAdapter  extends RecyclerView.Adapter<MyAlimentoRecyclerViewAdapter.ViewHolder> {

    //Se definen los valores que se pasarán como lista, en nuestro caso un ArrayList de profesores
    private final ArrayList<Alimento> alimentos;
    private final AlimentoFragment.OnListFragmentInteractionListener mListener;

    public MyAlimentoRecyclerViewAdapter(ArrayList<Alimento> items, AlimentoFragment.OnListFragmentInteractionListener listener) {
        alimentos = items;
        mListener = listener;
    }

    //ViewHolder es la vista que se crea a partir de la view y que nos servirá para rellenar cada item con los datos
    //en el siguiente método. Esta clase la referenciaremos como holder en el siguiente método
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_alimento, parent, false);
        return new ViewHolder(view);
    }

    @Override

    //Método importante ya que es el que dibuja cada elemento de la lista, de manera que
    // actua lanzandose en bucle y se llama cada vez que se tiene que pintar una fila
    // el valor position va variando
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Alimento alimento;
        //holder es el elemento que recoge el objeto a mostrar según la posicion
        alimento = alimentos.get(position);

        //asignamos el objeto del modelo a los componentes visuales

        holder.textViewNombre.setText(alimento.getNombre());
        holder.textViewCantidad.setText(alimento.getCantidad());
        holder.textViewPrecio.setText(alimento.getPrecio());
        holder.textViewDistribuidor.setText(alimento.getDistribuidor());
        holder.textViewTipo.setText(alimento.getTipo());

        //En este punto escuchamos los eventos y se lo pasamos al listener que teniamos referenciado desde el principio
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(alimento);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return alimentos.size();
    }


    // Clase interna que se define para mapear los contenidos que están definidos
    // en nuestro Layout para los items de RecyclerView. Es lo que permitirá al adapter
    // pintar en la pantalla cada fila de profesor.

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView textViewNombre;
        public final TextView textViewCantidad;
        public final TextView textViewPrecio;
        public final TextView textViewDistribuidor;
        public final TextView textViewTipo;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewNombre =  view.findViewById(R.id.nombre);
            textViewCantidad =  view.findViewById(R.id.cantidad);
            textViewPrecio =  view.findViewById(R.id.precio);
            textViewDistribuidor =  view.findViewById(R.id.distribuidor);
            textViewTipo =  view.findViewById(R.id.tipo);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewNombre.getText() + "'";
        }
    }
}