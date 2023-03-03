package com.example.ivan_fernandez_mendez.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.ivan_fernandez_mendez.modelo.Ruta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DaoRuta {
    private FirebaseFirestore db;
    private String collecionNombre;

    public DaoRuta(String collecionNombre) {
        db=FirebaseFirestore.getInstance();
        this.collecionNombre = collecionNombre;
    }

    public void addRuta(Ruta ruta){
        db.collection(collecionNombre).add(ruta);
    }

    public ArrayList<Ruta> getRutas(){
        ArrayList<Ruta> rutas=new ArrayList<Ruta>();
        db.collection(collecionNombre).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        Ruta ruta=new Ruta(new Double(document.getData().get("longInicial").toString()),
                                new Double(document.getData().get("latInicial").toString()), new Double(document.getData().get("rumbo").toString()),
                                new Double(document.getData().get("distancia").toString()));
                        Log.d("::::Dato", document.getId() + "=>" + ruta.getLongInicial()+","+ruta.getLatInicial()+","+ruta.getRumbo()+","+ruta.getDistancia());
                        rutas.add(ruta);
                    }
                } else {
                    Log.d("::::Dato", "Error getting documents: ", task.getException());
                }
            }
        });
        return rutas;
    }
}
