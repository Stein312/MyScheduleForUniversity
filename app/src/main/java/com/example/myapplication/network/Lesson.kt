package com.example.myapplication.network

import java.util.*

data class Lesson(
    val id:Long,
    val title: String,       //название
    val date: String, //дата
    val nameTch:String,     //имя преподователь
    val weekday:String, //день недели
    val time:String, //время
    val group:String, //группа
    val cabinet:String,
    val faculty:String
)

