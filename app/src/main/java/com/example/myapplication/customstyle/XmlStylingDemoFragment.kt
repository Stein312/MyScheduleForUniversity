package com.example.myapplication.customstyle

import android.os.Bundle
import android.view.View
import com.example.myapplication.BaseFragment
import com.example.myapplication.R
import ru.cleverpumpkin.calendar.CalendarView
import kotlinx.android.synthetic.main.fragment_calendar.*

class XmlStylingDemoFragment : BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_demo_styling

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(toolbarView) {
            setTitle(R.string.demo_styling)
            setNavigationIcon(R.drawable.ic_arrow_back_24dp)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }

        if (savedInstanceState == null) {
            calendarView.setupCalendar(selectionMode = CalendarView.SelectionMode.MULTIPLE)
        }
    }

}