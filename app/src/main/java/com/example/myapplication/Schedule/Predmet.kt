package com.example.myapplication.Schedule

data class Predmet(
    val name: String,       //название
    val date: String, //дата
    val nameTch:String,     //имя преподователь
    val day:String, //день недели
    val time:String, //время
    val  group:String, //группа
    val cabinet:String
){

    init {
        Schedule.listAllPred.add(this)
    }
}