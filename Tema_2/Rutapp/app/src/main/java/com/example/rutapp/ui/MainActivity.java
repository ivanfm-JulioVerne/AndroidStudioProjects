package com.example.rutapp.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.rutapp.R;
import com.example.rutapp.modelo.IPosicionDao;
import com.example.rutapp.modelo.Posicion;
import com.example.rutapp.modelo.PosicionConId;
import com.example.rutapp.modelo.PosicionDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<Posicion> ps=new ArrayList<Posicion>();
    static PosicionDao posicionDao=new PosicionDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.toolbar);

        ps.add(new Posicion(1,1,"D"));
        ps.add(new Posicion(2,1,"C"));
        ps.add(new Posicion(2,2,"B"));
        ps.add(new Posicion(1,2,"A"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.crear:
                crear();
                break;
            case R.id.mostrar:
                ArrayList<PosicionConId> pcis =posicionDao.mostrarVarios(ps);
                System.out.println("-------------");
                for(int i=0;i<pcis.size();i++){
                    System.out.println("Producto:");
                    System.out.println("- Id:" + pcis.get(i).getId());
                    System.out.println("- X:" + pcis.get(i).getX());
                    System.out.println("- Y:" + pcis.get(i).getY());
                    System.out.println("- Descripción:" + pcis.get(i).getDescripcion());
                }
                System.out.println("-------------");
                break;
            case R.id.actualizar:
                actualizar();
                break;
            case R.id.borrar:
                borrar();
                break;
        }
        return true;
    }

    public void crear(){
        Posicion posicion= new Posicion();
        LayoutInflater inflater = getLayoutInflater();
        View view=inflater.inflate(R.layout.linear_layout,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setNegativeButton("Cancel",(dialog,id)->{dialog.cancel();})
                .setView(view)
                .setTitle("Información")
                .setPositiveButton("OK",(dialog,id)->{
                    EditText editTextX=(EditText)(view.findViewById(R.id.posicionX));
                    posicion.setX(Integer.parseInt(editTextX.getText().toString()));

                    EditText editTextY=(EditText)(view.findViewById(R.id.posicionX));
                    posicion.setY(Integer.parseInt(editTextY.getText().toString()));

                    EditText editTextXDescripcion=(EditText)(view.findViewById(R.id.posicionX));
                    posicion.setDescripcion(editTextXDescripcion.getText().toString());

                    //System.out.println(posicion.getX() + " - " + posicion.getY()+ " - " + posicion.getDescripcion());

                    ps=posicionDao.crear(ps,posicion);
                });



                    AlertDialog dialogo= builder.create();

        dialogo.show();

    }

    public void actualizar(){
        Posicion posicion= new Posicion();
        LayoutInflater inflater = getLayoutInflater();
        View view=inflater.inflate(R.layout.linear_layout_actualizar,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setNegativeButton("Cancel",(dialog,id)->{dialog.cancel();})
                .setView(view)
                .setTitle("Información")
                .setPositiveButton("OK",(dialog,id)->{
                    EditText editTextId=(EditText)(view.findViewById(R.id.idPosicion));
                    int idPosicion=Integer.parseInt(editTextId.getText().toString());

                    EditText editTextX=(EditText)(view.findViewById(R.id.posicionX));
                    posicion.setX(Integer.parseInt(editTextX.getText().toString()));

                    EditText editTextY=(EditText)(view.findViewById(R.id.posicionX));
                    posicion.setY(Integer.parseInt(editTextY.getText().toString()));

                    EditText editTextXDescripcion=(EditText)(view.findViewById(R.id.posicionX));
                    posicion.setDescripcion(editTextXDescripcion.getText().toString());

                    //System.out.println(posicion.getX() + " - " + posicion.getY()+ " - " + posicion.getDescripcion());

                    ps=posicionDao.actualizar(ps,posicion,idPosicion);
                });



        AlertDialog dialogo= builder.create();

        dialogo.show();

    }

    public void borrar(){
        LayoutInflater inflater = getLayoutInflater();
        View view=inflater.inflate(R.layout.linear_layout_borrar,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setNegativeButton("Cancel",(dialog,id)->{dialog.cancel();})
                .setView(view)
                .setTitle("Información")
                .setPositiveButton("OK",(dialog,id)->{
                    EditText editTextId=(EditText)(view.findViewById(R.id.idPosicion));
                    int idPosicion=Integer.parseInt(editTextId.getText().toString());

                    //System.out.println(posicion.getX() + " - " + posicion.getY()+ " - " + posicion.getDescripcion());

                    ps=posicionDao.borrar(ps,idPosicion);
                });



        AlertDialog dialogo= builder.create();

        dialogo.show();

    }
}