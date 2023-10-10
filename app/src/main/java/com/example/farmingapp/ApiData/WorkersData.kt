package com.example.farmingapp.ApiData

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Workers Table")
data class WorkersData(
    @PrimaryKey
    val id: String,
    val age: Any?,
    val f_name: String?,
    val l_name: String?,
    val password: String,
    val phone: Int,
    val title: String?
)