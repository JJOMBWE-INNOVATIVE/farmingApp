package com.example.farmingapp.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.farmingapp.ApiData.AnimalList
import com.example.farmingapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {
   private var animalLiveData: MutableLiveData<List<com.example.farmingapp.ApiData.Response>> = MutableLiveData()

    fun getAllAnimal(){
        RetrofitInstance.api.getAnimalImage().enqueue(object : Callback<AnimalList>{
            override fun onResponse(call: Call<AnimalList>, response: Response<AnimalList>) {

                response.body()?.let { animal ->
                    animalLiveData.postValue(animal.response)
                }

            }

            override fun onFailure(call: Call<AnimalList>, t: Throwable) {
                Log.e("HomeViewModel",t.message.toString())
            }

        })

    }
    fun observerAnimalLiveData():LiveData<List<com.example.farmingapp.ApiData.Response>>{
        return animalLiveData
    }


}