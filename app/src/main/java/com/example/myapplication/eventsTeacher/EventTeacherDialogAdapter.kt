package com.example.myapplication.eventsTeacher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplication.R

class EventTeacherDialogAdapter(
    context: Context,
    events: Array<EventTeacherItem>
) : ArrayAdapter<EventTeacherItem>(context, 0, events) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dialog_event, parent, false)

        val eventItem = getItem(position)

        if (eventItem != null) {
            view.findViewById<TextView>(R.id.eventTimeView).text=eventItem.time
            view.findViewById<TextView>(R.id.titleGroup).text = eventItem.eventName
        }

        return view
    }

}