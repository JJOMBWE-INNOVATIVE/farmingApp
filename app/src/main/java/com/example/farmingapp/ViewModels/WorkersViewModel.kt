package com.example.farmingapp.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.farmingapp.ApiData.AnimalList
import com.example.farmingapp.ApiData.ResponseX
import com.example.farmingapp.ApiData.WorkersData
import com.example.farmingapp.ApiData.WorkersListX
import com.example.farmingapp.db.WorkersDataBase
import com.example.farmingapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkersViewModel(
    private val workersDataBase: WorkersDataBase
):ViewModel() {

    val myWorkersLiveData = workersDataBase.workersDao().getAllWorkers()
    val workersLiveData = MutableLiveData<List<ResponseX>>()

    fun getAllWorkers(){
        RetrofitInstance.api.getWokers().enqueue(object : Callback<WorkersListX> {
            override fun onResponse(call: Call<WorkersListX>, response: Response<WorkersListX>) {
                response.body()?.let { worker ->
                    workersLiveData.postValue(worker.response)
                }
            }

            override fun onFailure(call: Call<WorkersListX>, t: Throwable) {
                Log.e("WorkersViewModel",t.message.toString())
            }

        })

    }



    fun observeWorkersLiveData():LiveData<List<ResponseX>> {
        return workersLiveData
    }
    fun observeMyWorkersLiveData():LiveData<List<WorkersData>>{
        return myWorkersLiveData
    }


}