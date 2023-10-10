package com.example.farmingapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.farmingapp.ApiData.AddAnimal

@Dao
interface AddAnimalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddAnimal(addAnimal: AddAnimal)

    @Delete
    suspend fun deleteAddAnimal(addAnimal: AddAnimal)

    @Query("SELECT * FROM addanimal")
    fun getAllAnimals(): LiveData<List<AddAnimal>>
}
