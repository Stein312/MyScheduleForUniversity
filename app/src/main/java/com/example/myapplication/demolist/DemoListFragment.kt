package com.example.myapplication.demolist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.BaseFragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_demo_list.*

class DemoListFragment : BaseFragment() {

    interface OnDemoItemSelectionListener {

        fun onDemoItemSelected(demoItem: DemoItem)
    }

    override val layoutRes: Int
        get() = R.layout.fragment_demo_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val demoListAdapter = DemoListAdapter(
            onDemoItemClickListener = { demoItem ->
                (activity as? OnDemoItemSelectionListener)?.onDemoItemSelected(demoItem)
            }
        )

        with(recyclerView) {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(context)
            adapter = demoListAdapter
        }
    }

}