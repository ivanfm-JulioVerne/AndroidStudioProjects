package com.example.guardar_usuario_ivan_fernandez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Usuario> listaUsuarios=new ArrayList<Usuario>();

    TextInputEditText nombre;
    TextInputEditText apellido;

    RadioButton rbHombre;
    RadioButton rbMujer;
    RadioButton rbOtros;

    CheckBox cbDam;
    CheckBox cbDaw;
    CheckBox cbAsir;
    CheckBox cbOtros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre=(TextInputEditText)findViewById(R.id.nombre);
        apellido=(TextInputEditText)findViewById(R.id.apellido);

        cbDam=((CheckBox)findViewById(R.id.cbDam));
        cbDaw=((CheckBox)findViewById(R.id.cbDaw));
        cbAsir=((CheckBox)findViewById(R.id.cbAsir));
        cbOtros=((CheckBox)findViewById(R.id.cbOtros));

        rbHombre=(RadioButton)findViewById(R.id.rbHombre);
        rbMujer=(RadioButton)findViewById(R.id.rbMujer);
        rbOtros=(RadioButton)findViewById(R.id.rbOtros);

    }

    public void guardar(View view){
        String genero="";

        boolean cbDamCheck=cbDam.isChecked();
        boolean cbDawCheck=cbDaw.isChecked();
        boolean cbAsirCheck=cbAsir.isChecked();
        boolean cbOtrosCheck=cbOtros.isChecked();


        if (rbHombre.isChecked())
            genero="Hombre";
        else if (rbMujer.isChecked())
            genero="Mujer";
        else if (rbOtros.isChecked())
            genero="Otros";



            listaUsuarios.add(new Usuario(nombre.getText().toString(), apellido.getText().toString(),
                    genero, cbDamCheck, cbDawCheck, cbAsirCheck, cbOtrosCheck));

            Toast.makeText(this, "Usuario Guardado", Toast.LENGTH_LONG).show();

            borrar(findViewById(R.id.bBorrar));

    }

    public void borrar(View view){

        nombre.setText("");
        apellido.setText("");
        rbHombre.setChecked(false);
        rbMujer.setChecked(false);
        rbOtros.setChecked(false);
        cbDam.setChecked(false);
        cbDaw.setChecked(false);
        cbAsir.setChecked(false);
        cbOtros.setChecked(false);
    }

    public void mostrar(View view){
        Intent intentMostrar=new Intent(this, Mostrar.class);
        intentMostrar.putExtra("listaUsuarios",listaUsuarios);

        startActivity(intentMostrar);
    }
}