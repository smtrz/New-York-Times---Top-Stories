package com.task.nytimes.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.nytimes.Adapters.NewsAdapter
import com.task.nytimes.Helpers.ProgressDialogHelper
import com.task.nytimes.Helpers.UIHelper.showSnackToast
import com.task.nytimes.Interfaces.CardViewClick
import com.task.nytimes.Interfaces.NewsListInterface
import com.task.nytimes.Models.TopStories
import com.task.nytimes.R
import com.task.nytimes.ViewModels.NewsActivityViewModel
import kotlinx.android.synthetic.main.act_toolbar.*
import kotlinx.android.synthetic.main.activity_main.*


class NewsActivity : AppCompatActivity(), NewsListInterface, View.OnClickListener,CardViewClick {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.more -> {

                //popMenu()

            }
            R.id.btn_layout -> {
                //  newsViewModel.callNews()


            }
        }


    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var progressdialog: ProgressDialog;
    override fun ifListisEmpty(count: Int) {
        if (adapter.itemCount === 0) {
            rv_repos?.setVisibility(View.GONE)
            btn_layout.setVisibility(View.VISIBLE)
            error_layout.setVisibility(View.VISIBLE)
            // empty_view?.setVisibility(View.VISIBLE)
        } else {
            rv_repos?.setVisibility(View.VISIBLE)
            error_layout.setVisibility(View.GONE)
            btn_layout.setVisibility(View.VISIBLE)

            //  empty_view?.setVisibility(View.GONE)
        }
    }

    lateinit var newsViewModel: NewsActivityViewModel
    lateinit var adapter: NewsAdapter
    internal var list: TopStories? = null


   // internal var a: TopStories? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        newsViewModel.getResult().observe(this,
            Observer<TopStories> { articles ->
                //   Log.d("##","inside...")
                list = articles
                adapter.loadItems(articles, this, this)
                adapter.notifyDataSetChanged()
            })

        newsViewModel.ifDataIsloading().observe(this,
            Observer { aBoolean ->
                if (aBoolean!!) {
                    Log.d("##", "inside pd...")

                    //val ph = ProgressDialogHelper()
                    progressdialog = ProgressDialogHelper.showDialog(this@NewsActivity)


                } else {
                    if (progressdialog != null) {
                        ProgressDialogHelper.cancelDialog(progressdialog)
                    }
                }
            })

    }

/*    fun popMenu() {

        val popupMenu: PopupMenu = PopupMenu(this, more)
        popupMenu.menuInflater.inflate(R.menu.popupmenu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.action_sort_star -> {
                    adapter.loadItems(newsViewModel.sorted_allItems_bystar, this)
                    adapter.notifyDataSetChanged()
                }
                R.id.action_sort_name -> {

                    adapter.loadItems(newsViewModel.sorted_allItems_byname, this)
                    adapter.notifyDataSetChanged()

                }

            }

            true
        })
        popupMenu.show()
    *///}

    fun init() {

        more.setOnClickListener(this)
        btn_layout.setOnClickListener(this)
        rv_repos?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // setting up recyclerview and also binding activity with the view-model
        newsViewModel = ViewModelProviders.of(this).get(NewsActivityViewModel::class.java)
       newsViewModel.callNewsAPI()
        adapter = NewsAdapter(this, list?.results, list, this)
        rv_repos?.setAdapter(adapter)
        // pull to refresh
        //  pullToRefresh?.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
        //      newsViewModel.callNewsAPI()
        //     pullToRefresh?.setRefreshing(false)
        // })


    }

    override fun Onclick(pos:Int) {
        try {
            // going to the next activity with paracable data packet
            val i = Intent(this@NewsActivity, News_Detail_Activity::class.java)
            i.putExtra("news", list)
            i.putExtra("position",pos)
            startActivity(i)
        } catch (e: Exception) {
            Log.d("##","error: "+e.message)
            //showSnackToast(parent_layout, "Unable to load the selected news article.")
        }

    }

}
