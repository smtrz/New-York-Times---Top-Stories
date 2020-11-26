package com.task.nytimes.Database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.nytimes.Models.Results

@Dao
interface TrendingRepoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertnews(items: Results): Long


    @Query("SELECT * from newsresult ORDER BY id DESC")
     fun getallbookmarks(): LiveData<List<Results>>


}