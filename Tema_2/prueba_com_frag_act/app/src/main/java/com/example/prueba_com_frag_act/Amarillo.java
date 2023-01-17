package com.example.prueba_com_frag_act;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Amarillo extends Fragment {
    public OnClickIncrementar listener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amarillo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view=getView();

        Button bIncrementa=(Button) view.findViewById(R.id.b_inc);

        bIncrementa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.pulsaIncrementar();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnClickIncrementar) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " Se debe implementar OnClickIncrementar");
        }

    }
}