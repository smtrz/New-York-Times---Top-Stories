package com.task.nytimes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.task.nytimes.Database.DbRepository
import com.task.nytimes.Models.TopStories
import com.task.nytimes.ViewModels.NewsActivityViewModel
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch


@RunWith(AndroidJUnit4::class)

class TopStoriesTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    var dbrep: DbRepository? = DbRepository()
    lateinit var viewModel: NewsActivityViewModel
    lateinit var topSto: TopStories
    lateinit var data_observer: Observer<TopStories>


    @Mock
    lateinit var lifecycleOwner: LifecycleOwner
    var lifecycle: Lifecycle? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        //lifecycleOwner
        lifecycle = LifecycleRegistry(lifecycleOwner)
        viewModel = NewsActivityViewModel()


    }

    // testing top stories api
    @Test
    @Throws(InterruptedException::class)
    //suspend
    fun TestTopStoriesApi() {

        var signal: CountDownLatch = CountDownLatch(1)
        viewModel.callNewsAPI()


        data_observer = Observer<TopStories> { it ->
            signal.countDown()
            topSto = it

        }
        viewModel.getResult().observeForever(data_observer)
        signal.await()

        Assert.assertEquals(topSto?.status, "OK");


    }

    @After
    @Throws(java.lang.Exception::class)
    fun tearDown() {
        viewModel.getResult().removeObserver(data_observer)
    }

}