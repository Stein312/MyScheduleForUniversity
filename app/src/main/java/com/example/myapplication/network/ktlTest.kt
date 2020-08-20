package com.example.myapplication.network

import com.example.myapplication.network.ServerBuilder.Companion.getInstance
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


val lesson = GlobalScope.async(Dispatchers.IO) {
    val response= getInstance()!!.getApi().getAllAsync()
    if(response.isSuccessful){
        response.body()!![1]
        return@async
    }
}

fun main()= runBlocking(Dispatchers.IO){
    launch {  println(lesson.await())}.join()

    println("Hello")

}

