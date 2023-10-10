package com.example.farmingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.farmingapp.ApiData.PurchaseData

@Database(entities = [PurchaseData::class], version = 1, exportSchema = false)
abstract class PurchaseDataBase : RoomDatabase() {
    abstract fun purchaseDao(): PurchaseDao

    companion object {
        @Volatile
        var INSTANCE: PurchaseDataBase? = null

        @Synchronized
        fun getInstance(context: Context): PurchaseDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    PurchaseDataBase::class.java,
                    "purchaseRm"
                )
                    .fallbackToDestructiveMigration()
                    .addMigrations(Migration(1,2){
                            database ->
                        database.execSQL("ALTER TABLE PurchaseData ADD COLUMN new_column INTEGER NOT NULL DEFAULT 0")
                    } )
                    .build()
            }
            return INSTANCE as PurchaseDataBase
        }}}


