package com.example.myapplication.eventsTeacher

import ru.cleverpumpkin.calendar.CalendarDate
import ru.cleverpumpkin.calendar.CalendarView

class EventTeacherItem(
override val date: CalendarDate,
override val color: Int,
val time:String,
val eventName: String,
val cabinet:String,
val techer:String

) : CalendarView.DateIndicator