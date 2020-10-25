package com.example.myapplication.network

import com.example.myapplication.ListSchedule.faculty
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerApi {
    @GET("getAll")
    suspend fun getAllCoroutin():List<Lesson>
    @GET("/faculty/{faculty}")
    suspend fun getFaculty(@Path("faculty") faculty:String
    ):List<Lesson>
    @GET("titleGroup/getAll")
    fun getGroupList():Call<List<TitleGroup>>
    @GET("getAll")
    fun getAll():Call<List<Lesson>>
    @GET("myserver/get/{id}")
    fun get(@Path("id") int: Long):Call<Lesson>
    @GET("myserver/reload")
    fun reload():Call<String>
}