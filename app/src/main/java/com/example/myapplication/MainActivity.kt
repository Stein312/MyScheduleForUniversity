package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.customstyle.CodeStylingDemoFragment
import com.example.myapplication.dateboundaries.DateBoundariesDemoFragment
import com.example.myapplication.demolist.DemoItem
import com.example.myapplication.demolist.DemoListFragment

import com.example.myapplication.events.EventListDemoFragment
import com.example.myapplication.eventsTeacher.EventTeacherListFragment
import com.example.myapplication.network.ScheduleRepository
import com.example.myapplication.network.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.coroutines.launch
import ru.cleverpumpkin.calendar.CalendarView


class MainActivity : AppCompatActivity(), DemoListFragment.OnDemoItemSelectionListener {
    val repository=ScheduleRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository.getAllLess(
            onComplete = {lessons->
                ListSchedule.lessons.addAll(lessons)
                Log.d("Observer","Date to server")
            },
            onError = {
                Log.d("Observer","${it.message}")
                Log.d("Observer","NO date to server")
            }
        )
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, DemoListFragment())
                .commit()
        }

    }
    override fun onDemoItemSelected(demoItem: DemoItem) {
        val demoFragment = when (demoItem) {
            DemoItem.SELECTION -> EventTeacherListFragment()
            DemoItem.DATE_BOUNDARIES -> DateBoundariesDemoFragment()
            DemoItem.STYLING -> CodeStylingDemoFragment()
            DemoItem.EVENTS -> EventListDemoFragment()

        }


            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, demoFragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()

    }
}
