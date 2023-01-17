package com.example.prueba_reciclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba_reciclerview.modelo.Pokemon;

import java.util.ArrayList;

public class AdapterPokemonRecyclerView extends RecyclerView.Adapter<AdapterPokemonRecyclerView.ViewHolder> {

    //Se definen los valores que se pasarán como lista, en nuestro caso un ArrayList de profesores
    private final ArrayList<Pokemon> pokemons;
    private final ReciclerView.ListenerFragmentActivity mListener;

    public AdapterPokemonRecyclerView(ArrayList<Pokemon> items, ReciclerView.ListenerFragmentActivity listener) {
        pokemons = items;
        mListener = listener;
    }

    //ViewHolder es la vista que se crea a partir de la view y que nos servirá para rellenar cada item con los datos
    //en el siguiente método. Esta clase la referenciaremos como holder en el siguiente método
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recicler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override

    //Método importante ya que es el que dibuja cada elemento de la lista, de manera que
    // actua lanzandose en bucle y se llama cada vez que se tiene que pintar una fila
    // el valor position va variando
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Pokemon pokemon;
        //holder es el elemento que recoge el objeto a mostrar según la posicion
        pokemon = pokemons.get(position);

        //asignamos el objeto del modelo a los componentes visuales

        holder.textViewNombre.setText(pokemon.getNombre());
        holder.textViewTipo.setText(pokemon.getTipo());
        holder.textViewNivel.setText(pokemon.getNivel()+"");

        //En este punto escuchamos los eventos y se lo pasamos al listener que teniamos referenciado desde el principio
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.lista(pokemon);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView textViewNombre;
        public final TextView textViewTipo;
        public final TextView textViewNivel;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewNombre =  view.findViewById(R.id.nombre);
            textViewTipo =  view.findViewById(R.id.tipo);
            textViewNivel =  view.findViewById(R.id.nivel);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewNombre.getText() + "'";
        }
    }
}

