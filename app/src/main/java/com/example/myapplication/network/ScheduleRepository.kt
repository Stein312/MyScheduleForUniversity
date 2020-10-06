package com.example.myapplication.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class ScheduleRepository {
    suspend fun getAllLesson():List<Lesson>{
        return Networking.serverApi.getAllAsync()
    }
    suspend fun getGroupList():List<String>{
        return Networking.serverApi.getGroupList()
    }
    fun getAllLess(
        onComplete:(List<Lesson>)->Unit,
        onError:(Throwable)->Unit
    ){
        Networking.serverApi.getAll().enqueue(
            object:Callback<List<Lesson>>{
                override fun onFailure(call: Call<List<Lesson>>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(
                    call: Call<List<Lesson>>,
                    response: Response<List<Lesson>>
                ) {
                    if(response.isSuccessful){
                        onComplete(response.body().orEmpty())
                    }
                    else{
                        onError(RuntimeException("incorect status code"))
                    }
                }

            }
        )
    }
}