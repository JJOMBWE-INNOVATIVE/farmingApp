package com.example.farmingapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.farmingapp.ApiData.SalesData
import com.example.farmingapp.db.SalesDataBase

class SalesListViewModel(
    private val salesDataBase: SalesDataBase
):ViewModel() {

    private val mySalesListLiveData = salesDataBase.salesDao().getAllSales()


    fun observeMySalesLiveData():LiveData<List<SalesData>>{
        return mySalesListLiveData
    }


}