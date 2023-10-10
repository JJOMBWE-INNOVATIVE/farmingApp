package com.example.farmingapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.farmingapp.ApiData.SalesData


@Dao
interface SalesDao {

    @Insert


   suspend fun insertNewSale(salesData: SalesData)

   @Delete
   suspend fun deleteSale(salesData: SalesData)

   @Update (onConflict = OnConflictStrategy.REPLACE)
   suspend fun upsert(salesData: SalesData)

   @Query("SELECT*FROM `sales`")
   fun getAllSales():LiveData<List<SalesData>>



}