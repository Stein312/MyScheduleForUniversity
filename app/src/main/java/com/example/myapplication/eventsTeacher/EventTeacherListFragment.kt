package com.example.myapplication.eventsTeacher

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.BaseFragment
import com.example.myapplication.ListSchedule
import com.example.myapplication.R
import com.example.myapplication.Schedule.Schedule
import com.example.myapplication.network.ScheduleRepository
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.coroutines.launch
import ru.cleverpumpkin.calendar.CalendarDate
import ru.cleverpumpkin.calendar.CalendarView
import ru.cleverpumpkin.calendar.extension.getColorInt
import java.util.*

class EventTeacherListFragment : BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_techer_event

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(toolbarView) {
            setTitle(R.string.faculty_of_computer_science_and_mathematics)
            setNavigationIcon(R.drawable.ic_arrow_back_24dp)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
        lifecycleScope.launch {
        calendarView.datesIndicators = generateEventItems()}

        calendarView.onDateClickListener = { date ->
            showDialogWithEventsForSpecificDate(date)

        }


        if (savedInstanceState == null) {
            calendarView.setupCalendar(selectionMode = CalendarView.SelectionMode.NONE)
        }
    }

    private fun showDialogWithEventsForSpecificDate(date: CalendarDate) {
        val eventTeacherItems = calendarView.getDateIndicators(date)
            .filterIsInstance<EventTeacherItem>()
            .toTypedArray()

        if (eventTeacherItems.isNotEmpty()) {
            val adapter = EventTeacherDialogAdapter(requireContext(), eventTeacherItems)
            val myDialogClickListener= DialogInterface.OnClickListener{
                    _, which ->
                val builder2= AlertDialog.Builder(requireContext())
                    .setTitle(eventTeacherItems[which].eventName)
                    .setMessage("""Кабинет: ${eventTeacherItems[which].cabinet} 
                        |Преподователь:${eventTeacherItems[which].teacher}""".trimMargin())
                val dialog2=builder2.create()
                dialog2.show()
            }
            val builder = AlertDialog.Builder(requireContext())
                .setTitle("$date")
                .setAdapter(adapter, myDialogClickListener)

            val dialog = builder.create()
            dialog.show()

        }

    }

    suspend fun generateEventItems(): List<EventTeacherItem> {

        val schedule= Schedule(
            ScheduleRepository().getAllLesson(),
            ListSchedule.titleGroup)
        val rasp =schedule
        val calendar: Calendar = GregorianCalendar(2020, Calendar.JANUARY,21)
        val context = requireContext()
        val eventTeacherItems = mutableListOf<EventTeacherItem>()

        repeat(300) {
            val arr =rasp.getShedlIsDatetoTeacher("Истомин В.В.",calendar.time)
            if(arr.size!=0) {
                eventTeacherItems += EventTeacherItem(
                    eventName = arr[0].title,
                    date = CalendarDate(calendar.time),
                    color = if (arr[0].title == "") context.getColorInt(R.color.event_0_color)
                    else context.getColorInt(R.color.event_1_color),
                    time = arr[0].time,
                    cabinet = arr[0].cabinet,
                    teacher = arr[0].nameTch
                )

                eventTeacherItems += EventTeacherItem(
                    eventName = arr[1].title,
                    date = CalendarDate(calendar.time),
                    color = if (arr[1].title == "") context.getColorInt(R.color.event_0_color)
                    else context.getColorInt(R.color.event_2_color),
                    time = arr[1].time,
                    cabinet = arr[1].cabinet,
                    teacher = arr[1].nameTch
                )

                eventTeacherItems += EventTeacherItem(
                    eventName = arr[2].title,
                    date = CalendarDate(calendar.time),
                    color = if (arr[2].title == "" && arr[4].title == "") context.getColorInt(R.color.event_0_color)
                    else context.getColorInt(R.color.event_3_color),
                    time = arr[2].time,
                    cabinet = arr[2].cabinet,
                    teacher = arr[2].nameTch
                )

                eventTeacherItems += EventTeacherItem(
                    eventName = arr[3].title,
                    date = CalendarDate(calendar.time),
                    color = if (arr[3].title == "" && arr[5].title == "" && arr[6].title == "") context.getColorInt(
                        R.color.event_0_color
                    )
                    else context.getColorInt(R.color.event_4_color),
                    time = arr[3].time,
                    cabinet = arr[3].cabinet,
                    teacher = arr[3].nameTch
                )

                eventTeacherItems += EventTeacherItem(
                    eventName = arr[4].title,
                    date = CalendarDate(calendar.time),
                    color = if (arr[4].title == "") context.getColorInt(R.color.event_0_color)
                    else context.getColorInt(R.color.event_5_color),
                    time = arr[4].time,
                    cabinet = arr[4].cabinet,
                    teacher = arr[4].nameTch
                )
                eventTeacherItems += EventTeacherItem(
                    eventName = arr[5].title,
                    date = CalendarDate(calendar.time),
                    color = context.getColorInt(R.color.event_5_color),
                    time = arr[5].time,
                    cabinet = arr[5].cabinet,
                    teacher = arr[5].nameTch
                )
                eventTeacherItems += EventTeacherItem(
                    eventName = arr[6].title,
                    date = CalendarDate(calendar.time),
                    color = context.getColorInt(R.color.event_5_color),
                    time = arr[6].time,
                    cabinet = arr[6].cabinet,
                    teacher = arr[6].nameTch
                )
            }

            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return eventTeacherItems
    }

}