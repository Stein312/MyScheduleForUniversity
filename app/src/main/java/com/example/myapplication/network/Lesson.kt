package com.example.myapplication.network

data class Lesson(
    val title: String,       //название
    val date: String, //дата
    val nameTch:String,     //имя преподователь
    val weekday:String, //день недели
    val time:String, //время
    val group:String, //группа
    val cabinet:String
)
data class LessonResponse (val results: List<Lesson>)
