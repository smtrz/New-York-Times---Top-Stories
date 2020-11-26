package com.task.nytimes.Database


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.nytimes.Configurations.App
import com.task.nytimes.Helpers.DateHelper
import com.task.nytimes.Helpers.UIHelper
import com.task.nytimes.Interfaces.EndpointsInterface
import com.task.nytimes.Models.Results
import com.task.nytimes.Models.TopStories
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
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
    internal var topStories = MutableLiveData<TopStories>()

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


    fun getNewData(): MutableLiveData<TopStories> {
        return topStories

    }

    // checking if the data has been loaded successfully.
    fun ifDataIsloading(): MutableLiveData<Boolean> {
        return dataLoading

    }


    // add new bookmark news insertion API
    suspend fun insertnewNews(sms: Results) {
        // insertNewsAsync(trendingDao).execute(sms)
        val row = trendingDao.insertnews(sms)
        coroutineScope {
            MainScope()
            if (row > 0) {
                //  Handler(Looper.getMainLooper()).post {
                UIHelper.showLongToastInCenter(c, "Bookmarked")
                //}
            } else {
                // Handler(Looper.getMainLooper()).post {
                UIHelper.showLongToastInCenter(c, "Already Bookmarked the Story")

                //}
            }
        }


    }


    // get the bookmark news
    fun getbookMarkNews(): LiveData<List<Results>> {
        // return getallbookmarkedNews(trendingDao).execute().get()

        return trendingDao.getallbookmarks()
    }


/*    inner class insertNewsAsync internal constructor(private val mAsyncTaskDao: TrendingRepoDao) :
        AsyncTask<Results, Void, Void>() {

        override fun doInBackground(vararg params: Results): Void? {
            val row = mAsyncTaskDao.insertnews(params[0])
            //  Log.d("ROWCHECK", row.toString())
            if (row > 0) {
                Handler(Looper.getMainLooper()).post {
                    UIHelper.showLongToastInCenter(c, "Bookmarked")
                }
            } else {
                Handler(Looper.getMainLooper()).post {
                    UIHelper.showLongToastInCenter(c, "Already Bookmarked the Story")

                }
            }
            return null
        }
    }*/

    /* private inner class getallbookmarkedNews internal constructor(private val mAsyncTaskDao: TrendingRepoDao) :
         AsyncTask<Void, Void, LiveData<List<Results>>>() {
         override fun doInBackground(vararg url: Void): LiveData<List<Results>> {
             return mAsyncTaskDao.getallbookmarks()
         }
     }
 */
    // get news data /////////
    suspend fun getNewsData() {


        dataLoading.value = true
        //  pd.show();
        val endpoints = retrofit!!.create(EndpointsInterface::class.java)
        endpoints.getNewsListData("ZNxvQVUF3mr4VgB2VbJcy5zztaXIqbch")
            .enqueue(object : Callback<TopStories> {
                override fun onResponse(
                    call: Call<TopStories>,
                    response: Response<TopStories>
                ) {
                    dataLoading.value = false

                    if (response.isSuccessful) {
                        topStories.postValue(response.body())
                    } else {
                        topStories.postValue(null)
                    }
                }

                override fun onFailure(call: Call<TopStories>, t: Throwable) {
                    // topStories = MutableLiveData<TopStories>()
                    topStories.postValue(TopStories())
                    dataLoading.postValue(false)

                }
            })
    }


    suspend fun shouldFetchData() {
        getNewsData();

    }
}
