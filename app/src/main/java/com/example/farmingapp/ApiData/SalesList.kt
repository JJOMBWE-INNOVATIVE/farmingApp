package com.example.farmingapp.ApiData

data class SalesList(
    val errorCode: Int,
    val message: String,
    val salesData: List<SalesData>
)