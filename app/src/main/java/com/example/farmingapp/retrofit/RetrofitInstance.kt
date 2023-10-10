package com.example.farmingapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {


    val api : FarmApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://ivansfarm.000webhostapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(FarmApi::class.java)
    }




}