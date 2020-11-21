package com.task.nytimes.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.task.nytimes.Activities.News_Detail_Activity
import com.task.nytimes.Adapters.NewsAdapter
import com.task.nytimes.Helpers.UIHelper
import com.task.nytimes.Interfaces.CardViewClick
import com.task.nytimes.Interfaces.NewsListInterface
import com.task.nytimes.Models.TopStories
import com.task.nytimes.R
import com.task.nytimes.ViewModels.NewsActivityViewModel
import kotlinx.android.synthetic.main.newsfragment.*

/**
 * A simple [Fragment] Bookmark.
 */
class BookmarkFragment : Fragment(), NewsListInterface, CardViewClick {
    lateinit var newsViewModel: NewsActivityViewModel
    lateinit var adapter: NewsAdapter
    internal var list: TopStories? = TopStories()
    var isFragmentLoaded = false
    var result = ""

    fun init() {
// creating and initialzing a gridview
        rv_repos?.layoutManager = GridLayoutManager(activity, 2)

        // setting up recyclerview and also binding activity with the view-model
        newsViewModel = ViewModelProviders.of(this).get(NewsActivityViewModel::class.java)
        // setting up adapter
        adapter = NewsAdapter("bookmark", activity!!, list?.results, list, this)
        rv_repos?.setAdapter(adapter)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.newsfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initialzing the fragment
        init()
        //setting up view controller and observing it.
        newsViewModel.getbookMarkNews().observe(this, Observer {
            list!!.results = ArrayList(it)

            adapter.loadItems(list!!, this, this)
            adapter.notifyDataSetChanged()


        })

    }

    override fun ifListisEmpty(count: Int) {

        if (count == 0) {
            result = "No Bookmarks found"
        } else {
            result = ""


        }


    }

    override fun Onclick(pos: Int) {
        try {
            // going to the next activity with paracable data packet
            val i = Intent(activity, News_Detail_Activity::class.java)
            i.putExtra("news", list)
            i.putExtra("position", pos)
            startActivity(i)
        } catch (e: Exception) {

        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser && !isFragmentLoaded) {

            // just checking if the fragment is visible and we need to check if the data is present
            // we also need to hide  the swipeview as i have used the same layout for both the fragments and pull to refresh is not usable here.
            if (result != "") {
// letting user know bookmarks are currently empty
                UIHelper.showLongToastInCenter(activity, result)
            }
            pullToRefresh?.isEnabled = false
            pullToRefresh?.isRefreshing = false
            isFragmentLoaded = true
        } else {
            pullToRefresh?.isEnabled = true
            pullToRefresh?.isRefreshing = true

            isFragmentLoaded = false

        }
    }

    override fun bookMarkClicked(pos: Int) {

        // this functon is unusable here.
    }
}

