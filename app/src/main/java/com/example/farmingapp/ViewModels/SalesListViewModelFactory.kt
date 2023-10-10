package com.example.farmingapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.farmingapp.ApiData.SalesData
import com.example.farmingapp.db.SalesDataBase

class SalesListViewModelFactory(
   private val salesDataBase: SalesDataBase
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SalesListViewModel(salesDataBase) as T
    }




}