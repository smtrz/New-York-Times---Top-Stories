package com.task.nytimes.Database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.task.nytimes.Models.Multimedia

class Converters {

// for string array conversion
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }
}