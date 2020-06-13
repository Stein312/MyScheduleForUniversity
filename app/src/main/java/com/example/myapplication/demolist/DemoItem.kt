package com.example.myapplication.demolist

import androidx.annotation.StringRes
import com.example.myapplication.R

enum class DemoItem(@StringRes val titleRes: Int) {
    SELECTION(titleRes = R.string.demo_selection),
    DATE_BOUNDARIES(titleRes = R.string.demo_date_boundaries),
    STYLING(titleRes = R.string.demo_styling),
    EVENTS(titleRes = R.string.demo_events),

}