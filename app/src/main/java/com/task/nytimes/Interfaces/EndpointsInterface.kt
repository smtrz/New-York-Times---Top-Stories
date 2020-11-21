package com.task.nytimes.Interfaces


import com.task.nytimes.Models.TopStories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndpointsInterface {

    @GET("topstories/v2/world.json")
    fun getNewsListData(
        @Query("api-key") api: String
    ): Call<TopStories>

}



