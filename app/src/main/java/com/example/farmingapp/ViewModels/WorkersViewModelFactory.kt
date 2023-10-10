package com.example.farmingapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.farmingapp.db.WorkersDataBase

class WorkersViewModelFactory(
   private val workersDataBase: WorkersDataBase
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WorkersViewModel(workersDataBase) as T
    }




}