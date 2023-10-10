package com.example.farmingapp.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters


@TypeConverters
class WorkersTypeConverter {


    @TypeConverter
    fun fromAnyToString(attribute: Any?): String {
        if (attribute == null) {
            return ""
        }

        return attribute.toString()
    }

    @TypeConverter
    fun fromStringToAny(attribute: String?): Any? {
        if (attribute == null || attribute.isEmpty()) {
            return null
        }

        // You can handle conversions to other data types here if needed
        // For now, just return the string as is
        return attribute
    }






}