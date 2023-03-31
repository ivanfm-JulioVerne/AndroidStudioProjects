package com.example.examen_mariajose_valverde_ada.conexionFirestore;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.examen_mariajose_valverde_ada.modelo.Ruta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UsoFirestore {
    FirebaseFirestore db;
    String id;
    public UsoFirestore(){
        db = FirebaseFirestore.getInstance();
        id="";
    }

    public void addRuta(FirebaseFirestore db,Ruta ruta){
        Map<String, Object> rutas=new HashMap<>();
        rutas.put("longInicial", ruta.getLongInicial());
        rutas.put("latInicial", ruta.getLatInicial());
        rutas.put("rumbo", ruta.getRumbo());
        rutas.put("distancia", ruta.getDistancia());
        db.collection("rutasmariajose_valverde")
                .add(rutas)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("aa", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("aa", "Error adding document", e);
                    }
                });
    }

    public MutableLiveData<ArrayList<Ruta>> getRutas(FirebaseFirestore db){
        MutableLiveData<ArrayList<Ruta>>rutasNombres=new MutableLiveData<>();
        ArrayList<Ruta>rutasArray=new ArrayList<>();
        db.collection("rutasmariajose_valverde")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("aaa", document.getId() + " => " + document.getData());
                                Map<String, Object> rutas= document.getData();
                                rutasArray.add(new Ruta(Integer.parseInt(rutas.get("longInicial").toString()),
                                        Integer.parseInt(rutas.get("latInicial").toString()),
                                        Integer.parseInt(rutas.get("rumbo").toString()),
                                        Integer.parseInt(rutas.get("distancia").toString())));
                            }
                        } else {
                            Log.w("aaa", "Error getting documents.", task.getException());
                        }
                    }
                });
        rutasNombres.postValue(rutasArray);
        return rutasNombres;
    }


    public FirebaseFirestore getDb(){
        return db;
    }
}
