package com.example.myapplication.network


import com.example.myapplication.ListSchedule
import com.example.myapplication.Schedule.Schedule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.timer


fun main() {
        val listGroup = listOf<String>(
                "ПМ-0119",
                "ПМ-0218",
                "ПМ-0317",
                "ПМ-0416",
                "ПИ-0119",
                "ПИ-0218",
                "ПИ-0416",
                "ИВТ-0317"
        )
        val repository=ScheduleRepository()
        val calendar: Calendar = GregorianCalendar(2020,Calendar.JANUARY, 21)
        repository.getAllLess(
                onComplete = {lessons->
                        ListSchedule.lessons.addAll(lessons)
                        println(ListSchedule.lessons.size)
                        Schedule(ListSchedule.lessons,listGroup).getShedlIsDate("ПИ-0119",calendar.time).forEach{
                                println(it)
                        }
                },
                onError = {
                        ListSchedule.lessons.addAll(emptyList())
                        println("Ошибка")
                }
        )
        println(ListSchedule.lessons.size)

}



