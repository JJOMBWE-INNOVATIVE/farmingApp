package com.example.farmingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.farmingapp.ApiData.SalesData


@Database(entities = [SalesData::class], version = 2, exportSchema = false)
abstract class SalesDataBase : RoomDatabase(){
    abstract fun salesDao():SalesDao
    companion object {
        @Volatile
        var INSTANCE: SalesDataBase? = null

        @Synchronized
        fun getInstance(context: Context): SalesDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    SalesDataBase::class.java,
                    "salesRm"
                )
                    .fallbackToDestructiveMigration()
                    .addMigrations(Migration(2,3){
                            database ->
                        database.execSQL("ALTER TABLE PurchaseData ADD COLUMN new_column INTEGER NOT NULL DEFAULT 0")
                    } )
                    .build()
            }
            return INSTANCE as SalesDataBase
        }}}