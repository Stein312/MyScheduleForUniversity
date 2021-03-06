package com.example.myapplication.events

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import com.example.myapplication.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.Schedule.Schedule
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.item_dialog_event.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import ru.cleverpumpkin.calendar.CalendarDate
import ru.cleverpumpkin.calendar.CalendarView
import ru.cleverpumpkin.calendar.extension.getColorInt
import java.io.FileInputStream
import java.io.InputStream
import java.util.*

class EventListDemoFragment : BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_calendar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(toolbarView) {
            setTitle(R.string.demo_events)
            setNavigationIcon(R.drawable.ic_arrow_back_24dp)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }

        calendarView.datesIndicators = generateEventItems()

        calendarView.onDateClickListener = { date ->
            showDialogWithEventsForSpecificDate(date)

        }


        if (savedInstanceState == null) {
            calendarView.setupCalendar(selectionMode = CalendarView.SelectionMode.NONE)
        }
    }

    private fun showDialogWithEventsForSpecificDate(date: CalendarDate) {
        val eventItems = calendarView.getDateIndicators(date)
            .filterIsInstance<EventItem>()
            .toTypedArray()

        if (eventItems.isNotEmpty()) {
            val adapter = EventDialogAdapter(requireContext(), eventItems)
            val myDialogClickListener=DialogInterface.OnClickListener{
                    _, which ->
                val builder2=AlertDialog.Builder(requireContext())
                    .setTitle("${eventItems[which].eventName}")
                    .setMessage("""Кабинет: ${eventItems[which].cabinet} 
                        |Преподователь:${eventItems[which].techer}""".trimMargin())
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

    private fun generateEventItems(): List<EventItem> {

        val input:InputStream =activity!!.assets.open ("AssetsSchedule/FPMiI.xlsx")
        val xlWb= XSSFWorkbook(input)
        val rasp=Schedule(xlWb)
        val calendar:Calendar = GregorianCalendar(2020,Calendar.JANUARY,21)
        val context = requireContext()
        val eventItems = mutableListOf<EventItem>()

        repeat(300) {
           val arr=rasp.getShedlIsDate("ПИ-0119",calendar.time)
            if(arr.size!=0){
            eventItems += EventItem(
                eventName = arr[0].name ,
                date = CalendarDate(calendar.time),
                color = if(arr[0].name=="") context.getColorInt(R.color.event_0_color)
                else context.getColorInt(R.color.event_1_color),
                time = arr[0].time,
                cabinet = arr[0].cabinet,
                techer = arr[0].nameTch
            )

            eventItems += EventItem(
                eventName = arr[1].name,
                date = CalendarDate(calendar.time),
                color = if(arr[1].name=="") context.getColorInt(R.color.event_0_color)
                else context.getColorInt(R.color.event_2_color),
                time = arr[1].time,
                cabinet = arr[1].cabinet,
                techer = arr[1].nameTch
            )

            eventItems += EventItem(
                eventName = arr[2].name,
                date = CalendarDate(calendar.time),
                color = if(arr[2].name=="" && arr[4].name=="") context.getColorInt(R.color.event_0_color)
                else context.getColorInt(R.color.event_3_color),
                time = arr[2].time,
                cabinet = arr[2].cabinet,
                techer = arr[2].nameTch
                            )

                eventItems += EventItem(
                    eventName = arr[3].name,
                    date = CalendarDate(calendar.time),
                    color =if(arr[3].name=="" && arr[5].name==""&& arr[6].name=="") context.getColorInt(R.color.event_0_color)
                    else context.getColorInt(R.color.event_4_color),
                    time = arr[3].time,
                    cabinet = arr[3].cabinet,
                    techer = arr[3].nameTch
                )

                eventItems += EventItem(
                    eventName = arr[4].name,
                    date = CalendarDate(calendar.time),
                    color = if(arr[4].name=="") context.getColorInt(R.color.event_0_color)
                    else context.getColorInt(R.color.event_5_color),
                    time = arr[4].time,
                    cabinet = arr[4].cabinet,
                    techer = arr[4].nameTch
                )
                eventItems += EventItem(
                    eventName = arr[5].name,
                    date = CalendarDate(calendar.time),
                    color = context.getColorInt(R.color.event_5_color),
                    time = arr[5].time,
                    cabinet = arr[5].cabinet,
                    techer = arr[5].nameTch
                )
                eventItems += EventItem(
                    eventName = arr[6].name,
                    date = CalendarDate(calendar.time),
                    color = context.getColorInt(R.color.event_5_color),
                    time = arr[6].time,
                    cabinet = arr[6].cabinet,
                    techer = arr[6].nameTch
                )
                }

            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return eventItems
    }

}