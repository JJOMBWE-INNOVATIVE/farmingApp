package com.example.farmingapp.retrofit

import com.example.farmingapp.ApiData.AnimalList
import com.example.farmingapp.ApiData.SalesList
import com.example.farmingapp.ApiData.WorkersListX
import retrofit2.Call
import retrofit2.http.GET

interface FarmApi {

    @GET("get_sales.php")
    fun getSalesData(): Call<SalesList>

    @GET("get_animals.php")
    fun getAnimalImage():Call<AnimalList>

    @GET("get_users.php")
    fun getWokers():Call<WorkersListX>


}