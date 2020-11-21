package com.task.nytimes.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.task.nytimes.Activities.NewsActivity
import com.task.nytimes.Fragments.BookmarkFragment
import com.task.nytimes.Fragments.NewsFragment


class HomeTabBarAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        if (position == 0) {
           // NewsActivity.type = "news"
            return NewsFragment()

        } else {
        //    NewsActivity.type = "bookmark"

            return BookmarkFragment()
        }

        /*  return when (position) {
              0 ->
                  NewsFragment()
              //}
              1 ->
                  //{

                  BookmarkFragment()


              //}
              else ->
                  BookmarkFragment()

          }*/

    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Top Stories"
            1 -> "Bookmarks"
            else -> {
                return ""
            }
        }
    }
}