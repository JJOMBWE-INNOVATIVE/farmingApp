package com.example.farmingapp.ApiData

data class PurchaseList(
    val errorCode: Int,
    val message: String,
    val purchaseData: List<PurchaseData>
)