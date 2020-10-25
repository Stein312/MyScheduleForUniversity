package com.example.myapplication.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServerBuilder {
   companion object{ private var mInstance: ServerBuilder? = null

       fun getInstance(): ServerBuilder? {
           if (mInstance == null) {
               mInstance = ServerBuilder()
           }
           return mInstance
       }

   }

    private var retrofit:Retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()



    fun getApi():ServerApi{
        return retrofit.create(ServerApi::class.java)
    }

}