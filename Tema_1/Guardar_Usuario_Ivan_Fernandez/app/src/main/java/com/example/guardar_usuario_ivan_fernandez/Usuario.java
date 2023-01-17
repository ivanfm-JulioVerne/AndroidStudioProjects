package com.example.guardar_usuario_ivan_fernandez;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String apellido;
    private String genero;
    private boolean dam;
    private boolean daw;
    private boolean asir;
    private boolean otros;

    public Usuario(String nombre,String apellido,String genero,
                   boolean dam,boolean daw,boolean asir,boolean otros){
        this.nombre=nombre;
        this.apellido=apellido;
        this.genero=genero;
        this.dam=dam;
        this.daw=daw;
        this.asir=asir;
        this.otros=otros;
    }

    public String mostrar(){
        String dam="";
        String daw="";
        String asir="";
        String otros="";

        if (this.dam)
            dam="X";
        if (this.daw)
            daw="X";
        if (this.asir)
            asir="X";
        if (this.otros)
            otros="X";

        return "-----------------------\nNombre: "+this.nombre+"\nApellido: "+this.apellido+"\nGenero: "+this.genero+
                "\nTitulos:\n -Dam: "+dam+"\n -Daw: "+daw+"\n -Asir: "+asir+"\n -Otros: "+otros+"-----------------------";
    }
}
