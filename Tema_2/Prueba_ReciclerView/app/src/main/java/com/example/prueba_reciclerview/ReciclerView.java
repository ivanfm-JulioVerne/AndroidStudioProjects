package com.example.prueba_reciclerview;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prueba_reciclerview.modelo.BDPokemon;
import com.example.prueba_reciclerview.modelo.Pokemon;

import java.util.ArrayList;

public class ReciclerView extends Fragment {

    private int mColumnCount = 1;

    private ListenerFragmentActivity listener;

    private ArrayList<Pokemon> pokemons;

    private RecyclerView recyclerView;

    private AdapterPokemonRecyclerView adapterPokemonRecyclerView;

    public ReciclerView(){
        pokemons=new BDPokemon().getPokemons();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recicler_view, container, false);

        // Definir el reciclerview
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            //Se crea adapter que  recibe dos parámetros: Una lista de elementos y
            adapterPokemonRecyclerView=new AdapterPokemonRecyclerView(pokemons, listener);

            //El reciclerview recibe el adapter
            recyclerView.setAdapter(adapterPokemonRecyclerView);
        }

        Log.d("Dato",view.toString());
        return view;

        //return inflater.inflate(R.layout.fragment_recicler_view, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListenerFragmentActivity) {
            listener = (ListenerFragmentActivity) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " Se debe implementar ListenerFragmentActivity");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface ListenerFragmentActivity {
        public void lista(Pokemon item);
    }
}