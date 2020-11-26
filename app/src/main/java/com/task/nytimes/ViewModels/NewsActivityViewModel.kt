package com.task.nytimes.ViewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.nytimes.Configurations.App
import com.task.nytimes.Database.DbRepository
import com.task.nytimes.Models.Results
import com.task.nytimes.Models.TopStories
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsActivityViewModel() : ViewModel() {

    @Inject
    lateinit var dbrepo: DbRepository
    lateinit var bookmarks: LiveData<List<Results>>

    init {


        App.app.appLevelComponent.inject(this)
    }

    fun ifDataIsloading(): MutableLiveData<Boolean> {
        return dbrepo!!.ifDataIsloading()

    }
    // just refresh the data based on the result.

    fun callNewsAPI() {
        viewModelScope.launch {
            dbrepo!!.shouldFetchData()
        }

        // }

    }

    fun getResult(): MutableLiveData<TopStories> {
        return dbrepo!!.getNewData()

    }

    // add new bookmark news insertion API
    fun insertnewNews(sms: Results) {
        viewModelScope.launch {
            dbrepo.insertnewNews(sms)
        }
    }


    // get the bookmark news
    fun getbookMarkNews(): LiveData<List<Results>> {
    //    val result = MutableLiveData<List<Results>>()
        // viewModelScope.launch {
       return dbrepo.getbookMarkNews()
        //}
        //return result
    }
}


