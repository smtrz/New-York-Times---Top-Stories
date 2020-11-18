package com.task.nytimes.Database


import com.task.nytimes.Models.TopStories
import androidx.room.Database
import androidx.room.RoomDatabase

import com.task.nytimes.Models.BaseTrending

@Database(entities = [BaseTrending::class], version = 2, exportSchema = false)
abstract class AppDB : RoomDatabase() {


    abstract fun trendingDao(): TrendingRepoDao
}
