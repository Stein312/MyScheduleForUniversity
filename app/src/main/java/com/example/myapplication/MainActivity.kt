package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.demolist.DemoItem
import com.example.myapplication.demolist.DemoListFragment
import com.example.myapplication.events.EventListLessonFragment
import com.example.myapplication.network.ScheduleRepository


class MainActivity : AppCompatActivity(), DemoListFragment.OnDemoItemSelectionListener {
    val repository = ScheduleRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repository.getGroupList(
            onComplete = { titleGroup ->
                ListSchedule.titleGroup.addAll(titleGroup)
            },
            onError = {
                Log.d("Observer", "${it.message}")
                Log.d("Observer", "NO date to server")
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
            DemoItem.SOCIOLOGY_AND_JOURNALISM -> {
                ListSchedule.faculty= getString(R.string.faculty_sociology_and_journalism)

                EventListLessonFragment()
            }
            DemoItem.FACULTY_OF_LAW -> {
                ListSchedule.faculty= getString(R.string.faculty_of_law)

                EventListLessonFragment()
            }
            DemoItem.FACULTY_OF_ECONOMICS -> {
                ListSchedule.faculty= getString(R.string.faculty_of_economics)

                EventListLessonFragment()
            }
            DemoItem.COMPUTER_SCIENCE_AND_MATH ->{
                ListSchedule.faculty= getString(R.string.faculty_of_computer_science_and_mathematics)

                EventListLessonFragment()
            }
            DemoItem.FOREIGN_LANGUAGE->{
                ListSchedule.faculty= getString(R.string.foreign_language)
                EventListLessonFragment()
            }
            DemoItem.PSYCHOLOGY_AND_PEDAGOGY->{
                ListSchedule.faculty= getString(R.string.faculty_of_psychology_and_pedagogy)
                EventListLessonFragment()
            }

        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, demoFragment)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

    }
}
