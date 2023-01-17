package com.example.ivn_fernndez_examen1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalcularActivity extends AppCompatActivity {

    static String resutadoString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular);

        calcularVelocidad();
    }

    private void calcularVelocidad() {
        Bundle extras=this.getIntent().getExtras();

        int distancia=extras.getInt("distancia");
        int tiempo=extras.getInt("tiempo");
        String medida=extras.getString("medida");
        TextView text = findViewById(R.id.resultado);
        if (tiempo==0){
            resutadoString="No se puede dividir entre 0";

        }else {

            System.out.println(medida);

            float resultado = 0;

            switch (medida) {
                case "m/s":
                    resultado = distancia / tiempo;
                    System.out.println("m/s");
                    break;
                case "m/min":
                    resultado = (distancia * 60) / tiempo;
                    System.out.println("m/min");
                    break;
                case "km/h":
                    resultado = (distancia * 36) / (tiempo * 10);
                    System.out.println("km/h");
                    break;
                case "km/min":
                    resultado = (distancia * 6) / (tiempo * 100);
                    System.out.println("km/min");
                    break;
            }
            resutadoString = resultado + medida;
        }
        text.setText(resutadoString);
    }

    public void volver(View view){
        Intent intent=new Intent();
        intent.putExtra("resultado",resutadoString);
        setResult(1,intent);
        finish();
    }
}