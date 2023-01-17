package com.example.calcularmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void calcularMedia(View view){
        //Declaración de objetos
        TextView etiquetaResu =((TextView)findViewById(R.id.tResu));
        EditText num1 =((EditText)findViewById(R.id.eNum1));
        EditText num2 =(( EditText)findViewById(R.id.eNum2));
        Button boton=(Button) view;

        //Sacar información y pasarla a entero
        int int1=Integer.parseInt(num1.getText().toString());
        int int2=Integer.parseInt(num2.getText().toString());

        //Hacer la media
        float resu=(float)(int1+int2)/2;

        //Mostrar en pantalla el calculo
        etiquetaResu.setText(resu+"");
    }
}