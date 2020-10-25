package com.example.myapplication.network

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ScheduleViewModel : ViewModel() {
    private val repository = ScheduleRepository()

    private val lessonListLiveData = MutableLiveData<List<Lesson>>()
    private val facultyListLiveData = MutableLiveData<List<Lesson>>()
    //private val isLoadingLiveData = MutableLiveData<Boolean>()
    val liveDataLessons: LiveData<List<Lesson>>
        get() = lessonListLiveData
    val liveDataFacultyLesson: LiveData<List<Lesson>>
        get() = facultyListLiveData
    /*val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData*/

    fun getLessons() {
       // isLoadingLiveData.postValue(true)
        viewModelScope.launch {
            try {
                val lessons= repository.getAllLesson()
                lessonListLiveData.postValue(lessons)
            }catch (t:Throwable){
                lessonListLiveData.postValue(emptyList())
            }
        }

    }
    fun getLessonFaculty(faculty:String) {
        // isLoadingLiveData.postValue(true)
        viewModelScope.launch {
            try {
                val lessons= repository.getFaculty(faculty)
                facultyListLiveData.postValue(lessons)
            }catch (t:Throwable){
                facultyListLiveData.postValue(emptyList())
            }
        }

    }

}