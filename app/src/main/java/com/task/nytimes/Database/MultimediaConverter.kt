package com.task.nytimes.Database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.nytimes.Models.Multimedia
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

class MultimediaConverter {


    @TypeConverter
    fun storedStringToMyObjects(data: String?): ArrayList<Multimedia?>? {
        val gson = Gson()
        if (data == null) {
            return ArrayList(Collections.emptyList())
        }
        val listType: Type = object : TypeToken<ArrayList<Multimedia?>?>() {}.type
        return gson.fromJson<ArrayList<Multimedia?>>(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: ArrayList<Multimedia?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}