package com.example.recyclerviewmj.modelo;


import java.util.ArrayList;

public class BDEstaticaAlimentos {
    //base de datos que carga desde el constructor vacio todos los datos, getlista, set, remove y add
    static ArrayList<Alimento> alimentos=new ArrayList<>();

    public BDEstaticaAlimentos(ArrayList<Alimento> profesores) {
        this.alimentos = profesores;
    }
    public BDEstaticaAlimentos() {
        alimentos.add(new Alimento("Ternera","5.6","Mercedes","carne"));
        alimentos.add(new Alimento("Pescado","1.6","Juan","pescado"));
        alimentos.add(new Alimento("Pescado2","3.6","Juan","pescado"));
        alimentos.add(new Alimento("Zanahoria","5.6","Pepe","vegetal"));
        alimentos.add(new Alimento("Tomate","5.6","Pepe","vegetal"));
        alimentos.add(new Alimento("Lechuga","5.6","Pepe","vegetal"));

    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(ArrayList<Alimento> profesores) {
        this.alimentos = profesores;
    }

    public void removeAlimento(int pos){
        try{
            alimentos.remove(pos);
        }catch(Exception e){

        }
    }

    public void guardarAlimento(Alimento a){
        alimentos.add(a);
    }

}
