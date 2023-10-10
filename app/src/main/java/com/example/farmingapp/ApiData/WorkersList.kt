package com.example.farmingapp.ApiData

data class WorkersList(
    val message: String,
    val workersData: List<WorkersData>,
    val responseCode: Int
)