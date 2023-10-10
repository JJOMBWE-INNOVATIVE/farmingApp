package com.example.farmingapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.farmingapp.ApiData.PurchaseData
import com.example.farmingapp.ApiData.SalesData
import com.example.farmingapp.db.PurchaseDataBase
import com.example.farmingapp.db.SalesDataBase

class purchaseListViewModel(
    private var purchaseDataBase: PurchaseDataBase
):ViewModel() {

    private val myPurchaseLiveData = purchaseDataBase.purchaseDao().getAllPurchase()

    fun observeMySalesLiveData():LiveData<List<PurchaseData>>{
        return myPurchaseLiveData
    }


}