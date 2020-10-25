package com.example.myapplication.events

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.myapplication.BaseFragment
import com.example.myapplication.ListSchedule
import com.example.myapplication.R
import com.example.myapplication.Schedule.Schedule
import com.example.myapplication.network.Lesson
import com.example.myapplication.network.ScheduleViewModel
import com.example.myapplication.network.TitleGroup
import kotlinx.android.synthetic.main.fragment_calendar.*
import ru.cleverpumpkin.calendar.CalendarDate
import ru.cleverpumpkin.calendar.CalendarView
import ru.cleverpumpkin.calendar.extension.getColorInt
import java.util.*

class EventListLessonFragment : BaseFragment() {
    private val viewModel: ScheduleViewModel by viewModels()
    private val listGroup = mutableListOf<TitleGroup>()
    override val layoutRes: Int
        get() = R.layout.fragment_calendar
    lateinit var titleGroup: TitleGroup

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ListSchedule.titleGroup.forEach {
            if (it.faculty == ListSchedule.faculty)
                listGroup.add(it)
        }

        if (listGroup.isNotEmpty()) {
            titleGroup = listGroup[0]
            with(toolbarView) {
                title = titleGroup.title
                setNavigationIcon(R.drawable.ic_arrow_back_24dp)
                setNavigationOnClickListener { activity?.onBackPressed() }
            }
            bindViewModel()
        } else {
            with(toolbarView) {
                setTitle(R.string.app_name)
                setNavigationIcon(R.drawable.ic_arrow_back_24dp)
                setNavigationOnClickListener { activity?.onBackPressed() }
            }
        }

        calendarView.datesIndicators = generateEventItems(ListSchedule.lessons, listGroup)
        calendarView.onDateClickListener = { date ->
            showDialogWithEventsForSpecificDate(date)
        }
        btnGroup.setOnClickListener { showDialogButtonGroup(listGroup) }
        if (savedInstanceState == null) {
            calendarView.setupCalendar(selectionMode = CalendarView.SelectionMode.NONE)
        }
    }

    private fun bindViewModel() {
        viewModel.getLessonFaculty(faculty = ListSchedule.faculty)
        calendarView.isVisible = false
        viewModel.liveDataFacultyLesson.observe(viewLifecycleOwner) {
            ListSchedule.lessons.addAll(it)
            calendarView.isVisible = true
            calendarView.datesIndicators = generateEventItems(ListSchedule.lessons, listGroup)
            calendarView.onDateClickListener = { date ->
                showDialogWithEventsForSpecificDate(date)
            }
            progressBar.isVisible = false
            Log.d("Observer", "${ListSchedule.lessons.size}")
            Log.d("Observer", "Date to server coroutine")
        }


    }

    private fun showDialogButtonGroup(titleGroups: List<TitleGroup>) {
        val adapter = ButtonGroupDialogAdapter(requireContext(), titleGroups)
        val myDialogClickListener = DialogInterface.OnClickListener { _, which ->
            titleGroup = titleGroups[which]
            with(toolbarView) {
                title = titleGroup.title
                setNavigationIcon(R.drawable.ic_arrow_back_24dp)
                setNavigationOnClickListener { activity?.onBackPressed() }
            }
            calendarView.datesIndicators = generateEventItems(ListSchedule.lessons, listGroup)
            calendarView.onDateClickListener = { date ->
                showDialogWithEventsForSpecificDate(date)
            }
        }

        val builder = AlertDialog.Builder(requireContext())
            .setTitle(R.string.BtnGroup)
            .setAdapter(adapter, myDialogClickListener)
        if(titleGroups.isEmpty()) builder.setTitle("Отстуствует подключение к интернету")
        val dialog = builder.create()
        dialog.show()
    }

    private fun showDialogWithEventsForSpecificDate(date: CalendarDate) {

        val eventItems = calendarView.getDateIndicators(date)
            .filterIsInstance<EventItem>()
            .toTypedArray()

        if (eventItems.isNotEmpty()) {
            val adapter = EventDialogAdapter(requireContext(), eventItems)
            val myDialogClickListener = DialogInterface.OnClickListener { _, which ->
                val builder2 = AlertDialog.Builder(requireContext())
                    .setTitle(eventItems[which].eventName)
                    .setMessage(
                        """Кабинет: ${eventItems[which].cabinet} 
                        |Преподователь:${eventItems[which].teacher}""".trimMargin()
                    )
                val dialog2 = builder2.create()
                dialog2.show()
            }
            val builder = AlertDialog.Builder(requireContext())
                .setTitle("$date")
                .setAdapter(adapter, myDialogClickListener)

            val dialog = builder.create()
            dialog.show()

        }

    }

    private fun generateEventItems(
        listLesson: List<Lesson>,
        listGroup: List<TitleGroup>
    ): List<EventItem> {
        if (listGroup.isNotEmpty()) {
            val schedule = Schedule(listLesson, listGroup)
            val rasp = schedule

            val calendar: Calendar = GregorianCalendar(2020, Calendar.JANUARY, 21)
            val context = requireContext()
            val eventItems = mutableListOf<EventItem>()

            repeat(300) {
                val arr = rasp.getShedlIsDate(titleGroup.title, calendar.time)
                if (arr.size != 0) {
                    eventItems += EventItem(
                        eventName = arr[0].title,
                        date = CalendarDate(calendar.time),
                        color = if (arr[0].title == "") context.getColorInt(R.color.event_0_color)
                        else context.getColorInt(R.color.event_1_color),
                        time = arr[0].time,
                        cabinet = arr[0].cabinet,
                        teacher = arr[0].nameTch
                    )

                    eventItems += EventItem(
                        eventName = arr[1].title,
                        date = CalendarDate(calendar.time),
                        color = if (arr[1].title == "") context.getColorInt(R.color.event_0_color)
                        else context.getColorInt(R.color.event_2_color),
                        time = arr[1].time,
                        cabinet = arr[1].cabinet,
                        teacher = arr[1].nameTch
                    )

                    eventItems += EventItem(
                        eventName = arr[2].title,
                        date = CalendarDate(calendar.time),
                        color = if (arr[2].title == "" && arr[4].title == "") context.getColorInt(R.color.event_0_color)
                        else context.getColorInt(R.color.event_3_color),
                        time = arr[2].time,
                        cabinet = arr[2].cabinet,
                        teacher = arr[2].nameTch
                    )

                    eventItems += EventItem(
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

                    eventItems += EventItem(
                        eventName = arr[4].title,
                        date = CalendarDate(calendar.time),
                        color = if (arr[4].title == "") context.getColorInt(R.color.event_0_color)
                        else context.getColorInt(R.color.event_5_color),
                        time = arr[4].time,
                        cabinet = arr[4].cabinet,
                        teacher = arr[4].nameTch
                    )
                    eventItems += EventItem(
                        eventName = arr[5].title,
                        date = CalendarDate(calendar.time),
                        color = context.getColorInt(R.color.event_5_color),
                        time = arr[5].time,
                        cabinet = arr[5].cabinet,
                        teacher = arr[5].nameTch
                    )
                    eventItems += EventItem(
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

            return eventItems
        }
        return emptyList()
    }

}


