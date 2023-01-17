package com.example.prueba_com_frag_act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickIncrementar{

    Fragment color;
    int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        color=new Amarillo ();

        loadFragment(color,"amarillo");
    }

    private void loadFragment(Fragment f, String tag){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contenedor, f,tag)
                .commit();
    }
    private void replaceFragment(Fragment f,String tag){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, f,tag)
                .commit();
    }
    private void replaceAddToBack(Fragment f){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, f)
                .addToBackStack(null)
                .commit();
    }
    private void removeFragment(Fragment f){
        getSupportFragmentManager()
                .beginTransaction()
                .remove(f)
                .commit();
    }

    @Override
    public void pulsaIncrementar() {
        Log.d("HE pasao","2");
        Amarillo amarillo=(Amarillo) getSupportFragmentManager().findFragmentByTag("amarillo");
        Log.d("HE pasao","3");
        TextView tv=amarillo.getView().findViewById(R.id.textView);
        Log.d("HE pasao","4");
        tv.setText(num+"");
        Log.d("HE pasao","5");

        num++;
    }
}