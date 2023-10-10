package com.example.farmingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.farmingapp.ApiData.AddAnimal

@Database(entities = [AddAnimal::class], version = 1, exportSchema = false)
abstract class AddAnimalDataBase : RoomDatabase() {
    abstract fun addAnimalDao(): AddAnimalDao

    companion object {
        @Volatile
        var INSTANCE: AddAnimalDataBase? = null

        @Synchronized
        fun getInstance(context: Context): AddAnimalDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AddAnimalDataBase::class.java,
                    "addAnimalRm"
                ).fallbackToDestructiveMigration().build()
            }
            return INSTANCE as AddAnimalDataBase
        }
    }
}
