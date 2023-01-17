package com.example.ejercicio_1234;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int n=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n=10;
        if(savedInstanceState!=null){
            String nombre= (String) savedInstanceState.get("nombre");
            Toast.makeText(this, nombre, Toast.LENGTH_LONG).show();

        }
    }

    protected void onSaveInstanceState(Bundle saveInstanceState) {
        Toast.makeText(this, "Método saveInstanceState "+n, Toast.LENGTH_LONG).show();
        saveInstanceState.putString("nombre","Enrique");
        super.onSaveInstanceState(saveInstanceState);
    }

    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "Método onstart "+n, Toast.LENGTH_LONG).show();
    }
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Método onResume "+n, Toast.LENGTH_LONG).show();
    }
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Método onPause "+n, Toast.LENGTH_LONG).show();
    }
    protected void onStop() {
        super.onStop();
        n=5;
        Toast.makeText(this, "Método onStop "+n, Toast.LENGTH_LONG).show();
    }
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Método onDestroy "+ n, Toast.LENGTH_LONG).show();
    }

}
