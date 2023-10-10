package com.example.farmingapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.farmingapp.ApiData.WorkersData

@Dao
interface WorkersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewWorkers(workersData: WorkersData)

    @Delete
    suspend fun deleteWorkers(workersData: WorkersData)

    @Query("SELECT*FROM `workers table`")
    fun getAllWorkers(): LiveData<List<WorkersData>>



}