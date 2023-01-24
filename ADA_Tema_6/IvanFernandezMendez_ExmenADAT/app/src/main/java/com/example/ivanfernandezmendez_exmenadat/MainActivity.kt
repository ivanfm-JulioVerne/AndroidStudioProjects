package com.example.ivanfernandezmendez_exmenadat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ivanfernandezmendez_exmenadat.room.AppDB
import com.example.ivanfernandezmendez_exmenadat.room.entidades.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var grupo: Grupo =Grupo("2B","2º ESO B","I1")
        var alumno1:Alumno= Alumno("Iván","Fernández","2B")
        var alumno2:Alumno= Alumno("Ana","Fernández","2B")
        var asignatura:Asignatura= Asignatura("Físca y Química")
        var alumnoEstaAsignatura:AlumnoEstaAsignatura= AlumnoEstaAsignatura(1,1)
        var profesor: Profesor = Profesor("Pablo","García")
        var departamento:Departamento= Departamento("C","Ciencias",1)

        Log.d(":::Instituto","===================Insercción de datos===================")
        AppDB.getAppDB(applicationContext)!!.daoGrupo().crearGrupo(grupo)
        Log.d(":::Instituto","Se ha instertado un grupo")
        AppDB.getAppDB(applicationContext)!!.daoAlumno().crearAlumno(alumno1)
        AppDB.getAppDB(applicationContext)!!.daoAlumno().crearAlumno(alumno2)
        Log.d(":::Instituto","Se ha instertado un alumno")
        AppDB.getAppDB(applicationContext)!!.daoAsignatura().crearAsignatura(asignatura)
        Log.d(":::Instituto","Se ha instertado un asignatura")
        AppDB.getAppDB(applicationContext)!!.daoAlumnoEstaAsignatura().crearAlumnoEstaAsignatura(alumnoEstaAsignatura)
        Log.d(":::Instituto","Se ha instertado un alumnoEstaAsignatura")
        AppDB.getAppDB(applicationContext)!!.daoProfesor().crearProfesor(profesor)
        Log.d(":::Instituto","Se ha instertado un profesor")
        AppDB.getAppDB(applicationContext)!!.daoDepartamento().crearDepartamento(departamento)
        Log.d(":::Instituto","Se ha instertado un departamento")

        Log.d(":::Instituto","===================Lectura de alumnos por su grupo===================")
        val alumnosGrupo=AppDB.getAppDB(applicationContext)!!.daoAlumno().verAlumnosGrupo("2B")
        for (al in alumnosGrupo){
            Log.d(":::Institto","Alumno:" +
                    "\n - Id: ${al.id_alumno}" +
                    "\n - Nombre: ${al.nombre}" +
                    "\n - Apellido: ${al.apellido}" +
                    "\n - Grupo: ${al.id_grupo}")
        }

        Log.d(":::Instituto","===================Lectura de alumnos por su asignatura===================")
        val alumnosAsignatura=AppDB.getAppDB(applicationContext)!!.daoAlumno().verAlumnosAsignatura(1)
        for (al in alumnosAsignatura){
            Log.d(":::Institto","Alumno:" +
                    "\n - Id: ${al.id_alumno}" +
                    "\n - Nombre: ${al.nombre}" +
                    "\n - Apellido: ${al.apellido}" +
                    "\n - Grupo: ${al.id_grupo}")
        }

        Log.d(":::Instituto","===================Eliminación de datos===================")
        AppDB.getAppDB(applicationContext)!!.daoDepartamento().borrarDepartamento(AppDB.getAppDB(applicationContext)!!.daoDepartamento().verDepartamento("C"))
        Log.d(":::Instituto","Se ha eliminado un departamento")
        AppDB.getAppDB(applicationContext)!!.daoProfesor().borrarProfesor(AppDB.getAppDB(applicationContext)!!.daoProfesor().verProfesor(1))
        Log.d(":::Instituto","Se ha eliminado un profesor")
        AppDB.getAppDB(applicationContext)!!.daoAlumnoEstaAsignatura().borrarAlumnoEstaAsignatura(alumnoEstaAsignatura)
        Log.d(":::Instituto","Se ha eliminado un alumnoEstaAsignatura")
        AppDB.getAppDB(applicationContext)!!.daoAsignatura().borrarAsignatura(AppDB.getAppDB(applicationContext)!!.daoAsignatura().verAsignatura(1))
        Log.d(":::Instituto","Se ha eliminado un asignatura")
        AppDB.getAppDB(applicationContext)!!.daoAlumno().borrarAlumno(AppDB.getAppDB(applicationContext)!!.daoAlumno().verAlumno(1))
        AppDB.getAppDB(applicationContext)!!.daoAlumno().borrarAlumno(AppDB.getAppDB(applicationContext)!!.daoAlumno().verAlumno(2))
        Log.d(":::Instituto","Se ha eliminado un alumno")
        AppDB.getAppDB(applicationContext)!!.daoGrupo().borrarGrupo(AppDB.getAppDB(applicationContext)!!.daoGrupo().verGrupo("2B"))
        Log.d(":::Instituto","Se ha eliminado un grupo")




    }
}