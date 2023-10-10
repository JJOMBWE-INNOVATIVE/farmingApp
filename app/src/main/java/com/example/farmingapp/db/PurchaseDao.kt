package com.example.farmingapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.farmingapp.ApiData.PurchaseData

@Dao
interface PurchaseDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewPurchase(purchaseData: PurchaseData)

    @Delete
    suspend fun deletePurchase(purchaseData: PurchaseData)

    @Query("SELECT*FROM `purchase`")
    fun getAllPurchase(): LiveData<List<PurchaseData>>



}