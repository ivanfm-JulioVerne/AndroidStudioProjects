package com.example.repaso_examen_rec;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.repaso_examen_rec.modelo.Alimento;
import com.example.repaso_examen_rec.ui.alimentos.anadir_alimento.AnadirAlimento;
import com.example.repaso_examen_rec.ui.alimentos.ver_alimentos.BorrarAlimento;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.repaso_examen_rec.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AnadirAlimento, BorrarAlimento {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private BDSimulada bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_alimentos, R.id.nav_recetas, R.id.nav_gestion)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        bd=new BDSimulada();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void anadirAliemnto(Alimento nuevoAlimento) {

        Log.d(":::Añade","Añade");
        bd.addAlimento(nuevoAlimento);
    }

    public ArrayList<Alimento> getAlimentos() {
        return bd.getAlimentos();
    }

    @Override
    public void borrarAlimento(int position) {
        Alimento alimento=bd.getAlimento(position);
        String msgToast="Se ha borrado " + alimento.getNombre();
        Toast toast=Toast.makeText(getApplicationContext(),msgToast,Toast.LENGTH_SHORT);
        toast.show();
        bd.deleteAlimento(position);
    }
}