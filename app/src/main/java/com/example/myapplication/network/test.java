package com.example.myapplication.network;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class test {
    public static void main(String[] args) {

        ServerBuilder.Companion.getInstance()
                .getApi()
                .getAll()
                .enqueue(new Callback<List<Lesson>>() {
                    @Override
                    public void onResponse(Call<List<Lesson>> call, Response<List<Lesson>> response) {
                        List<Lesson> list=response.body();
                        for(Lesson lesson:list){
                            System.out.println(lesson);

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Lesson>> call, Throwable t) {

                    }
                });


    }
}
