package com.example.farmingapp.ApiData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales")
data class SalesData(
    val animal_id: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val price: String?,
    val quantity: String?,
    val selling_date: String?,
    val sold_by: String?,
    val sold_to: String?
)