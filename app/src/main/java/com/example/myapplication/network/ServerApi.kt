package com.example.myapplication.network

import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServerApi {
    @GET("myserver/getAll")
    suspend fun getAllAsync():Response<List<Lesson>>
    @GET("myserver/getAll")
    fun getAll():Call<List<Lesson>>
    @GET("myserver/get/{id}")
    fun get(@Path("id") int: Long):Call<Lesson>
    @GET("myserver/reload")
    fun reload():Call<String>
}