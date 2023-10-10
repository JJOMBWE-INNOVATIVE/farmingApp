package com.example.farmingapp.ApiData

data class AddAnimalList(
    val errorCode: Int,
    val message: String,
    val addAnimal: List<AddAnimal>
)