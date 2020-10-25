package com.example.myapplication.events

import ru.cleverpumpkin.calendar.CalendarDate
import ru.cleverpumpkin.calendar.CalendarView

class EventItem(
    override val date: CalendarDate,
    override val color: Int,
    val time:String,
    val eventName: String,
    val cabinet:String,
    val teacher:String


) : CalendarView.DateIndicator