package com.example.holamundo2022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pulsar(View view) {

        TextView etiqueta =((TextView)findViewById(R.id.textView));
        EditText numero =(( EditText)findViewById(R.id.numero1));
        Button boton=(Button) view;
        boton.setText("botón pulsado");

        int n=Integer.parseInt(numero.getText().toString());
        int resultado =n*2;

        etiqueta.setText(resultado+"");

        //salidas
        Toast.makeText(this,"esto es un mensaje",Toast.LENGTH_LONG).show();
        Log.d("mensajes","esto es un mensaje de prueba");
        System.out.println("otro mensaje");
    }
}