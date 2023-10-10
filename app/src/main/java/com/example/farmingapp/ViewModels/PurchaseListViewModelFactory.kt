package com.example.farmingapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.farmingapp.ApiData.SalesData
import com.example.farmingapp.db.PurchaseDataBase
import com.example.farmingapp.db.SalesDataBase

class PurchaseListViewModelFactory(
   private val purchaseDataBase: PurchaseDataBase
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return purchaseListViewModel(purchaseDataBase) as T
    }




}