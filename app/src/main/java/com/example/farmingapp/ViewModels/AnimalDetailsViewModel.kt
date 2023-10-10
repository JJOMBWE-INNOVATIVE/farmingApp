package com.example.farmingapp.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.farmingapp.ApiData.AddAnimal
import com.example.farmingapp.ApiData.AnimalList
import com.example.farmingapp.db.AddAnimalDataBase
import com.example.farmingapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalDetailsViewModel(
) : ViewModel() {

    private val animalDetailsLiveData: MutableLiveData<List<com.example.farmingapp.ApiData.Response>> =
        MutableLiveData()

    fun getAllAnimalImages() {
        RetrofitInstance.api.getAnimalImage().enqueue(object : Callback<AnimalList> {
            override fun onResponse(call: Call<AnimalList>, response: Response<AnimalList>) {
                response.body()?.let { animalList ->
                    animalDetailsLiveData.postValue(animalList.response)
                }
            }

            override fun onFailure(call: Call<AnimalList>, t: Throwable) {
                Log.e("animalDetailsViewModel", t.message.toString())
            }
        })
    }

    fun observerAnimalDetailsLiveData(): LiveData<List<com.example.farmingapp.ApiData.Response>> {
        return animalDetailsLiveData
    }

}
