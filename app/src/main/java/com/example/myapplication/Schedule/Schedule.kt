package com.example.myapplication.Schedule


import com.example.myapplication.network.Lesson
import org.apache.poi.ss.usermodel.Sheet
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Schedule(list:List<Lesson>,listGroup:List<String>) {

        var listAllLesson = list //Массив со всеми предметами

    var hashPredmet = HashMap<String, ArrayList<Lesson>>()


    init {
        listGroup.forEach { group->
            val listLesson=ArrayList<Lesson>()
        listAllLesson.forEach {
            if(it.group==group)
                listLesson.add(it)}
            hashPredmet[group]=listLesson
        }
    }
    fun getShedlIsDate(group: String, date: Date):ArrayList<Lesson> {
        val newlist=ArrayList<Lesson>()
        val format="dd.MM"
        val dateFormat= SimpleDateFormat(format)
        val stringDate:String=dateFormat.format(date)
        getShedlIsGroup(group)?.forEach {
            if (it.date == stringDate)
                newlist.add(it)
        }
        return newlist
    }
    fun getShedlIsDatetoTeacher(teacher: String, date: Date):ArrayList<Lesson> {
        val newlist=ArrayList<Lesson>()
        val format="dd.MM"
        val dateFormat= SimpleDateFormat(format)
        val stringDate:String=dateFormat.format(date)
        getShedlIsTecher(teacher)?.forEach {
            if (it.date== stringDate)
                newlist.add(it)
        }
        return newlist
    }
    fun getShedlIsTecher(nameTch:String):ArrayList<Lesson> {
        val newlist=ArrayList<Lesson>()
        listAllLesson.forEach {
            if(it.nameTch==nameTch)
                newlist.add(it)
        }
        return newlist
    }
    fun getShedlIsGroup(group: String) = hashPredmet[group]


}