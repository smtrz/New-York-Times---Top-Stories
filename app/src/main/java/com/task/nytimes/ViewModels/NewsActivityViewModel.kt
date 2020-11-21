package com.task.nytimes.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.nytimes.Configurations.App
import com.task.nytimes.Database.DbRepository
import com.task.nytimes.Models.Results
import com.task.nytimes.Models.TopStories
import javax.inject.Inject

class NewsActivityViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var dbrepo: DbRepository


    init {


        App.app.appLevelComponent.inject(this)
    }

    fun ifDataIsloading(): MutableLiveData<Boolean> {
        return dbrepo!!.ifDataIsloading()

    }
    // just refresh the data based on the result.

    fun callNewsAPI() {
        dbrepo!!.shouldFetchData()

    }

    fun getResult(): MutableLiveData<TopStories> {
        return dbrepo!!.getNewData()

    }
    // add new bookmark news insertion API
    fun insertnewNews(sms: Results) {
        dbrepo.insertnewNews(sms)
    }


    // get the bookmark news
    fun getbookMarkNews() : LiveData<List<Results>> {
        return dbrepo.getbookMarkNews()
    }
}


