package com.example.ivn_fernndez_examen1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String medida="km/h";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.ajuste:
                alertAjuste();
                System.out.println("Ajuste");
                break;
            case R.id.borrar:
                borrar();
                System.out.println("Borrar");
                break;
            case R.id.calcular:
                calcular();
                System.out.println("Calcular");
                break;
        }

        return true;
    }

    private void borrar() {
        EditText distancia=(EditText)findViewById(R.id.editNumDist);
        EditText tiempo=(EditText)findViewById(R.id.editNumTiempo);

        distancia.setText("");
        tiempo.setText("");
    }

    private void calcular() {
        Intent intent=new Intent(this, CalcularActivity.class);

        int distancia=Integer.parseInt(((EditText)findViewById(R.id.editNumDist))
                .getText().toString());
        int tiempo=Integer.parseInt(((EditText)findViewById(R.id.editNumTiempo))
                .getText().toString());

        intent.putExtra("distancia",distancia);
        intent.putExtra("tiempo",tiempo);
        intent.putExtra("medida",medida);

        startActivityForResult(intent,0);
    }

    public void alertAjuste(){
        String[] items=getResources().getStringArray(R.array.opciones);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int item) {
                        medida=items[item];
                        System.out.println(medida);
                    }}
        );


        AlertDialog dialogo= builder.create();

        dialogo.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Primer parámetro determina quien envió el primer intent
        //El segundo parámetro identifica el regreso
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            String mensaje = data.getExtras().getString("resultado");
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }

        }
    }