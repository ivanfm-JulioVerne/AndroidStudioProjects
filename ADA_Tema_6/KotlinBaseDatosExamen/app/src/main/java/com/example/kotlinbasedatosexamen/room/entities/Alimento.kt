package com.example.kotlinbasedatosexamen.room.entities

data class Alimento(
    var id_alimento:Int?,
    var nombre:String,
    var proteinas:Int,
    var grasas:Int,
    var hidratos:Int

){
    cosntructor (nombre:String,proteinas:Int,grasas:Int,hidratos:Int):this(id_alimento=){

    }
}
