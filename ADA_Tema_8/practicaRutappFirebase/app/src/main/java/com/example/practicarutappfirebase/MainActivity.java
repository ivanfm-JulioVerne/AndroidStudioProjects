package com.example.practicarutappfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.practicarutappfirebase.modelo.Lugar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lugar lugar=new Lugar(1.0,2.0,"Cadiz");
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Añadir documentos/modificar
        //Opcion controlada(modifica)
        //db.collection("lugar").document("2").set(lugar);
        //Opcion rapida y común
        //db.collection("lugar").add(lugar);

        //Leer un documento
        db.collection("lugar").document("2").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String nombre=documentSnapshot.getString("nombre");
                    Log.d("::::Rutapp",nombre);
                }
            }
        });

        //Leer varios documentos
        Query query=db.collection("lugar");
        db.collection("lugar").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("::::Rutapp", document.getId() + " => " + document.getData().get("latitud"));
                    }
                } else {
                    Log.d("::::Rutapp", "Error getting documents: ", task.getException());
                }
            }
        });
    }
}