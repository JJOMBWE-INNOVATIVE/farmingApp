package com.example.farmingapp.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmingapp.ApiData.SalesData
import com.example.farmingapp.ApiData.SalesList
import com.example.farmingapp.db.SalesDataBase
import com.example.farmingapp.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SalesDataViewModel(
    val salesDataBase: SalesDataBase
): ViewModel() {

    private var salesListLiveData = MutableLiveData<SalesList>()



    fun getSalesData() {
        RetrofitInstance.api.getSalesData().enqueue(object : Callback<SalesList>{
            override fun onResponse(
                call: Call<SalesList>,
                response: Response<SalesList>
            ) {
                if (response.isSuccessful){
                    val salesList = response.body()!!
                    if (salesList != null) {
                        salesListLiveData.postValue(salesList)
                    } else {
                        return
                    }
                } else {
                    // Handle unsuccessful response (e.g., error handling)
                    Log.d("NewSalesActivity", "Network request failed with code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<SalesList>, t: Throwable) {
                Log.d("NewSalesActivity", "Network request failed: ${t.message}")
            }
        })
    }

    fun observeSalesDataLiveData(): LiveData<SalesList> {
        return salesListLiveData
    }

    fun insertSales(salesData: SalesData) {
        viewModelScope.launch {
            salesDataBase.salesDao().insertNewSale(salesData)
        }
    }

    fun deleteSales(salesData: SalesData) {
        viewModelScope.launch {
            salesDataBase.salesDao().deleteSale(salesData)
        }
    }

    fun updateSales(salesData: SalesData) {
        viewModelScope.launch {
            salesDataBase.salesDao().insertNewSale(salesData)
        }
    }
}

