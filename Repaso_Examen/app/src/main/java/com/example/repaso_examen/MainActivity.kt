package com.example.repaso_examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.repaso_examen.room.AppDB
import com.example.repaso_examen.room.entidades.Alumno
import com.example.repaso_examen.room.entidades.Departamento
import com.example.repaso_examen.room.entidades.Instituto

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var instituto: Instituto = Instituto("Pepe Ruiz Vela","c/ Pepo, 12")
        AppDB.getAppDB(applicationContext)!!.daoInstituto().crearInstituto(instituto)

        var alumno:Alumno= Alumno("Iván Fernández Méndez","123456789L")
        AppDB.getAppDB(applicationContext)!!.daoAlumno().crearAlumno(alumno)

        var departamento:Departamento= Departamento("Física",1)
        AppDB.getAppDB(applicationContext)!!.daoDepartamento().crearDepartamento(departamento)

        val departamentos=AppDB.getAppDB(applicationContext)!!.daoDepartamento().verDepartamentos()
        Log.d(":::Institutos","::::::::::::::Lectura de Departamentos::::::::::::::")
        for (dep in departamentos){
            Log.d(":::Institutos","Id: ${dep.id_departamento}\n" +
                    "Nombre: ${dep.nombre}\n" +
                    "Instituto: ${dep.id_instituto}")
            AppDB.getAppDB(applicationContext)!!.daoDepartamento().borrarDepartamento(dep)
        }

        val institutos=AppDB.getAppDB(applicationContext)!!.daoInstituto().verInstitutos()
        Log.d(":::Institutos","::::::::::::::Lectura de instutos::::::::::::::")
        for (ins in institutos){
            Log.d(":::Institutos","Id: ${ins.id_instituto}\n" +
                    "Nombre: ${ins.nombre}\n" +
                    "Direccion: ${ins.direccion}")
            AppDB.getAppDB(applicationContext)!!.daoInstituto().borrarInstituto(ins)
        }



        val alumnos=AppDB.getAppDB(applicationContext)!!.daoAlumno().verAlumnos()
        Log.d(":::Institutos","::::::::::::::Lectura de alumnos::::::::::::::")
        for (al in alumnos){
            Log.d(":::Institutos","Id: ${al.id_alumno}\n" +
                    "Nombre: ${al.nombre}\n" +
                    "DNI: ${al.dni}")
            AppDB.getAppDB(applicationContext)!!.daoAlumno().borrarAlumno(al)
        }
    }
}