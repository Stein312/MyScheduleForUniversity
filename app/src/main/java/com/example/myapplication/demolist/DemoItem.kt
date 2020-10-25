package com.example.myapplication.demolist

import androidx.annotation.StringRes
import com.example.myapplication.R

enum class DemoItem(@StringRes val titleRes: Int) {
    SOCIOLOGY_AND_JOURNALISM(titleRes = R.string.faculty_sociology_and_journalism),
    FACULTY_OF_LAW(titleRes = R.string.faculty_of_law),
    FACULTY_OF_ECONOMICS(titleRes = R.string.faculty_of_economics),
    COMPUTER_SCIENCE_AND_MATH(titleRes = R.string.faculty_of_computer_science_and_mathematics),
    FOREIGN_LANGUAGE(titleRes = R.string.foreign_language),
    PSYCHOLOGY_AND_PEDAGOGY(titleRes = R.string.faculty_of_psychology_and_pedagogy)


}