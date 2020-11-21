package com.task.nytimes.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.task.nytimes.Adapters.HomeTabBarAdapter
import com.task.nytimes.R
import kotlinx.android.synthetic.main.act_toolbar.*
import kotlinx.android.synthetic.main.activity_main.*


class NewsActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {
    companion object {
        var type: String = "news"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()


    }


    fun init() {

        // hiding the back button from the main
        backbtn.visibility = View.GONE
        val fragmentAdapter = HomeTabBarAdapter(supportFragmentManager)
        viewpager_main.offscreenPageLimit = 0

        viewpager_main.adapter = fragmentAdapter
        tabs_main.setupWithViewPager(viewpager_main)
        tabs_main.addOnTabSelectedListener(this)

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (tab?.position == 0) {
            type = "news"


        } else {

            type = "bookmark"
        }
        Log.d("##", "this is the type: " + type)

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        // TODO("Not yet implemented")
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        //TODO("Not yet implemented")
    }


}
