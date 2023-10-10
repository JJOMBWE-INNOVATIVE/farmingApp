package com.example.farmingapp.ApiData

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("AddAnimal")
data class AddAnimal(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val age: String?,
    val available: String?,
    val breed: String?,
    val color: String?,
    val gender: String?,
    val name: String?,
    val tag_number: String?,
    val type: String?,
    val weight: String?
)