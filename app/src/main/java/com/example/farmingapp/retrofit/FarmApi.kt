package com.example.farmingapp.retrofit

import com.example.farmingapp.ApiData.AnimalList
import com.example.farmingapp.ApiData.SalesList
import com.example.farmingapp.ApiData.WorkersListX
import com.example.farmingapp.ViewModels.AnimalListViewModel
import com.example.farmingapp.ViewModels.WorkersViewModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FarmApi {

    @GET("get_sales.php")
    fun getSalesData(): Call<SalesList>

    @GET("get_animals.php")
    fun getAnimalImage():Call<AnimalList>

    @GET("get_users.php")
    fun getWokers():Call<WorkersListX>

    @FormUrlEncoded
    @POST("insert_animal.php")
    suspend fun AddMyAnimals(@Field("tag_number") tagNumber:String,
                           @Field("name") animalName:String,
                           @Field("type") animalType:String,
                           @Field("breed") animalBreed:String,
                           @Field("image") animalImage:String,
                           @Field("age") animalAge:String,
                           @Field("gender") animalGender:String,
                           @Field("weight") animalWeight:String,
                           @Field("color") animalColor:String,
                           @Field("available") available:String
    ): AnimalListViewModel



    @FormUrlEncoded
    @POST("insert_user.php")
    suspend fun AddMyWorker(
        @Field("f_name") firstName: String,
        @Field("l_name") secondName: String,
        @Field("title") title: String,
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("gender") gender: String
    ): WorkersViewModel



}