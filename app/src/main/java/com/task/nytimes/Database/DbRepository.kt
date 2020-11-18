package com.task.nytimes.Database


import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.nytimes.Configurations.App
import com.task.nytimes.Helpers.DateHelper
import com.task.nytimes.Helpers.Sp_Get_Store_Data
import com.task.nytimes.Interfaces.EndpointsInterface
import com.task.nytimes.Models.BaseTrending
import com.task.nytimes.Models.TopStories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject

class DbRepository {
    lateinit var trendingDao: TrendingRepoDao
    @Inject
    lateinit var retrofit: Retrofit
    internal var dataLoading = MutableLiveData<Boolean>()
    internal var topStories  = MutableLiveData<TopStories>()

    @Inject
    lateinit var c: Context
    @Inject
    lateinit var db: AppDB
    @Inject
    lateinit var now: Date
    @Inject
    lateinit var dh: DateHelper
    internal constructor() {
        // empty constructor

    }

    constructor(application: Context) {
        App.app.appLevelComponent.inject(this@DbRepository)

        trendingDao = db!!.trendingDao()
    }



    fun getNewData() :MutableLiveData<TopStories> {
        return topStories

    }


    fun ifDataIsloading(): MutableLiveData<Boolean> {
        return dataLoading

    }
    fun get_sorted_list_star(): List<BaseTrending> {

        return getallArticles_sortedbystars(trendingDao).execute().get()

    }

    fun get_sorted_list_name(): List<BaseTrending> {

        return getallArticles_sortedbyName(trendingDao).execute().get()

    }

    fun deleteAllitems() {
        deleteAsync(trendingDao).execute()

    }

    fun insertItems(sms: List<BaseTrending>?) {
        insertAsyncTask(trendingDao).execute(sms)
    }

    private inner class getallArticles_sortedbystars internal constructor(private val mAsyncTaskDao: TrendingRepoDao) :
        AsyncTask<Void, Void, List<BaseTrending>>() {
        override fun doInBackground(vararg url: Void): List<BaseTrending> {
            return mAsyncTaskDao.sortbyStars()
            //return null
        }
    }


    private inner class getallArticles_sortedbyName internal constructor(private val mAsyncTaskDao: TrendingRepoDao) :
        AsyncTask<Void, Void, List<BaseTrending>>() {
        override fun doInBackground(vararg url: Void): List<BaseTrending> {
            return mAsyncTaskDao.sortbyName()
        }
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: TrendingRepoDao) :
        AsyncTask<List<BaseTrending>, Void, Void>() {

        override fun doInBackground(vararg params: List<BaseTrending>): Void? {
            mAsyncTaskDao.insertItem(params[0])
            return null
        }
    }

    private class deleteAsync internal constructor(private val mAsyncTaskDao: TrendingRepoDao) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg params: Void): Void? {
            mAsyncTaskDao.delete()
            return null
        }
    }


// get news data /////////
fun getNewsData() {


    dataLoading.value = true
    //  pd.show();
    val endpoints = retrofit!!.create(EndpointsInterface::class.java)
    endpoints.getNewsListData("ZNxvQVUF3mr4VgB2VbJcy5zztaXIqbch").enqueue(object : Callback<TopStories> {
        override fun onResponse(
            call: Call<TopStories>,
            response: Response<TopStories>
        ) {
            dataLoading.value = false

            if (response.isSuccessful) {
                topStories.value=response.body()
            } else {
                topStories.value=null
            }
        }

        override fun onFailure(call: Call<TopStories>, t: Throwable) {

            dataLoading.value = false

        }
    })
}


    fun shouldFetchData() {
        getNewsData();

    }
}
