package com.example.myapplication.network

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ScheduleViewModel : ViewModel() {
    private val repository = ScheduleRepository()

    private val lessonListLiveData = mutableListOf<Lesson>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    val lessonList: List<Lesson>
        get() = lessonListLiveData
    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData
    lateinit var isJob: Job

    fun getSchedule() {
        isLoadingLiveData.postValue(true)
        repository.getAllLess(
            onComplete = {lessons->
                isLoadingLiveData.postValue(false)
                lessonListLiveData.addAll(lessons)
            },
            onError = {
                isLoadingLiveData.postValue(false)
                lessonListLiveData.addAll(emptyList())
            }
        )
        /*isJob=viewModelScope.launch {
            isLoadingLiveData.postValue(true)
            try {
                val lessons = repository.getAllLesson()
                Log.d("ScheduleViewModel","${lessons.isEmpty()}")
                lessonListLiveData.postValue(lessons)

            } catch (t: Throwable) {
                lessonListLiveData.postValue(emptyList())
            } finally {
                isLoadingLiveData.postValue(false)
            }
        }*/



    }

}