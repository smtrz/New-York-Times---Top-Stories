package com.task.nytimes.Database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.nytimes.Models.Results

@Database(entities = [Results::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class, MultimediaConverter::class)
abstract class AppDB : RoomDatabase() {

    abstract fun trendingDao(): TrendingRepoDao
}
