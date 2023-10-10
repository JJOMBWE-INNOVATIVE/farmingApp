package com.example.farmingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.farmingapp.ApiData.WorkersData


@Database(entities = [WorkersData::class], version = 2, exportSchema = false)
@TypeConverters(WorkersTypeConverter::class)
abstract class WorkersDataBase:RoomDatabase() {

    abstract fun workersDao():WorkersDao
    companion object{
        @Volatile
        var INSTANCE : WorkersDataBase? = null

        @Synchronized
        fun getInstance(context: Context):WorkersDataBase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    WorkersDataBase::class.java,
                    "workersRm",
                ).fallbackToDestructiveMigration().addMigrations()
                    .build()
            }
            return INSTANCE as WorkersDataBase

        }
    }

}