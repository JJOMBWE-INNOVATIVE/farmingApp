package com.example.farmingapp.ApiData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase")
data class PurchaseData(
    val description: String?,
    @PrimaryKey
    val id: String,
    val price: String?,
    val product: String?,
    val purchased_from: String?,
    val purchased_on: String?,
    val quantity: String?
)

