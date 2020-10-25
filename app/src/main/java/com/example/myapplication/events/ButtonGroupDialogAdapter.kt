package com.example.myapplication.events

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.myapplication.R
import com.example.myapplication.network.TitleGroup

class ButtonGroupDialogAdapter(context: Context,
                               titleGroups: List<TitleGroup>
): ArrayAdapter<TitleGroup>(context,0,titleGroups) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.button_group_dialog, parent, false)

        val titleGroup = getItem(position)

        if (titleGroup != null) {
            view.findViewById<TextView>(R.id.titleGroup).text = titleGroup.title

        }
        else{
            view.findViewById<TextView>(R.id.titleGroup).text = "Нет доступа к интернету"
        }

        return view
    }



}