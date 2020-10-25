package com.example.myapplication.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class ScheduleRepository {

    suspend fun getAllLesson():List<Lesson>{
        return Networking.serverApi.getAllCoroutin()
    }
    suspend fun getFaculty(faculty:String):List<Lesson>{
        return Networking.serverApi.getFaculty(faculty)
    }

    fun getGroupList(
        onComplete:(List<TitleGroup>)->Unit,
        onError: (Throwable) -> Unit){
            Networking.serverApi.getGroupList().enqueue(
                object:Callback<List<TitleGroup>>{
                    override fun onFailure(call: Call<List<TitleGroup>>, t: Throwable) {
                        onError(t)
                    }

                    override fun onResponse(
                        call: Call<List<TitleGroup>>,
                        response: Response<List<TitleGroup>>
                    ) {
                        if(response.isSuccessful){
                            onComplete(response.body().orEmpty())
                        }
                        else{
                            onError(RuntimeException("incorrect status code"))
                        }
                    }

                }
            )
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