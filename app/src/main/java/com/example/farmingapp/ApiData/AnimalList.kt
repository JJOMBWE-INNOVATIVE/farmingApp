package com.example.farmingapp.ApiData

data class AnimalList(
    val errorCode: Int,
    val message: String,
    val response: List<Response>
)