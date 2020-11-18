package com.task.nytimes.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.nytimes.Models.BaseTrending
import com.task.nytimes.Models.TopStories

@Dao
interface TrendingRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(items: List<BaseTrending>)


    @Query("SELECT * from trendingrepos")
    fun getallItems(): LiveData<List<BaseTrending>>



    @Query("DELETE FROM trendingrepos")
    fun delete()

    @Query("SELECT * from trendingrepos ORDER BY stars DESC")
    fun sortbyStars(): List<BaseTrending>


    @Query("SELECT * from trendingrepos ORDER BY name")
    fun sortbyName(): List<BaseTrending>
}