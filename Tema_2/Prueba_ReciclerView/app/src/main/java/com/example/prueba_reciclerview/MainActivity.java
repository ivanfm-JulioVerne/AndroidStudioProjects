package com.example.prueba_reciclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.prueba_reciclerview.modelo.Pokemon;

public class MainActivity extends AppCompatActivity implements ReciclerView.ListenerFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void lista(Pokemon item) {
        Toast.makeText(this,item.getNombre(),Toast.LENGTH_LONG).show();
    }
}