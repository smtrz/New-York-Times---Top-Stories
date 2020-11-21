package com.task.nytimes.Fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.task.nytimes.Activities.News_Detail_Activity
import com.task.nytimes.Adapters.NewsAdapter
import com.task.nytimes.Helpers.ProgressDialogHelper
import com.task.nytimes.Interfaces.CardViewClick
import com.task.nytimes.Interfaces.NewsListInterface
import com.task.nytimes.Models.TopStories
import com.task.nytimes.R
import com.task.nytimes.ViewModels.NewsActivityViewModel
import kotlinx.android.synthetic.main.newsfragment.*


/**
 * A simple [Fragment] subclass.
 */
class NewsFragment() : Fragment(), NewsListInterface, CardViewClick,
    SwipeRefreshLayout.OnRefreshListener {
    lateinit var progressdialog: ProgressDialog;
    lateinit var newsViewModel: NewsActivityViewModel
    lateinit var adapter: NewsAdapter
    internal var list: TopStories? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater!!.inflate(R.layout.newsfragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPause() {

        if (progressdialog != null && progressdialog.isShowing) {

            progressdialog.dismiss()
        }
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initialzning the fragment
        init()
        // setting up viewmodel
        newsViewModel.getResult().observe(this,
            Observer<TopStories> { articles ->
                Log.d("##", "inside get result...")
                list = articles
                adapter.loadItems(articles, this, this)
                adapter.notifyDataSetChanged()
            })

        // listening to ifdataloading live data to make sure if the data is loaded and we can dismiss the dialog.
        newsViewModel.ifDataIsloading().observe(this,
            Observer { aBoolean ->
                if (aBoolean!!) {

                    progressdialog = ProgressDialogHelper.showDialog(activity!!)


                } else {
                    if (progressdialog != null) {
                        ProgressDialogHelper.cancelDialog(progressdialog)
                    }
                }
            })


    }

    override fun ifListisEmpty(count: Int) {
        if (adapter.itemCount === 0) {
            rv_repos?.setVisibility(View.GONE)
            error_layout.setVisibility(View.VISIBLE)
        } else {
            rv_repos?.setVisibility(View.VISIBLE)
            error_layout.setVisibility(View.GONE)
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

    override fun bookMarkClicked(pos: Int) {


        newsViewModel.insertnewNews(list?.results!!.get(pos))
    }


    fun init() {
        Log.d("##", "inside init...")
        rv_repos?.layoutManager =
            LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        // setting up recyclerview and also binding activity with the view-model
        newsViewModel = ViewModelProviders.of(this).get(NewsActivityViewModel::class.java)
        newsViewModel.callNewsAPI()
        adapter = NewsAdapter("news", activity!!, list?.results, list, this)
        rv_repos?.setAdapter(adapter)
        // pull to refresh
        pullToRefresh?.setOnRefreshListener(this)


    }

    override fun onRefresh() {
        newsViewModel.callNewsAPI()
        Log.d("##", "inside pr")
        pullToRefresh?.setRefreshing(false)
    }
}