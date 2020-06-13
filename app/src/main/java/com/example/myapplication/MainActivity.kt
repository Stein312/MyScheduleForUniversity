package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.customstyle.CodeStylingDemoFragment
import com.example.myapplication.dateboundaries.DateBoundariesDemoFragment
import com.example.myapplication.demolist.DemoItem
import com.example.myapplication.demolist.DemoListFragment

import com.example.myapplication.events.EventListDemoFragment
import com.example.myapplication.eventsTeacher.EventTeacherListFragment



class MainActivity : AppCompatActivity(), DemoListFragment.OnDemoItemSelectionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
