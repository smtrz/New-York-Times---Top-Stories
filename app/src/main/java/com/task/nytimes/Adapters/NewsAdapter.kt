package com.task.nytimes.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.squareup.picasso.Picasso
import com.task.nytimes.Interfaces.CardViewClick
import com.task.nytimes.Interfaces.NewsListInterface
import com.task.nytimes.Models.Results
import com.task.nytimes.Models.TopStories
import com.task.nytimes.R
import kotlinx.android.synthetic.main.newlist_item.view.*


class NewsAdapter(
    var context: Context,
    var articles: List<Results>?,
    var completedata: TopStories?,
    var cv: CardViewClick

)

    : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    fun loadItems(newItems: TopStories, ni: NewsListInterface, Cv: CardViewClick) {
        completedata = newItems
        cv = Cv
        articles = completedata?.results
        ni.ifListisEmpty(articles!!.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.newlist_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        try {

            holder.newsTitle!!.text = articles!![position].title
            holder.published_date!!.text = articles!![position].published_date
            holder.desc!!.text = articles!![position].abstract
            holder.author!!.text = articles!![position].byline
            if (articles!![position].multimedia!!.size > 0) {

                Picasso.get().load(articles!![position].multimedia!!.get(0).url)
                    .into(holder.img)

            }


            holder.cardView!!.setOnClickListener {
                TransitionManager.beginDelayedTransition(holder.cardView!!)
                cv.Onclick(position)

            }


        } catch (e: Exception) {
            //eat this one.
        }

    }

    override fun getItemCount(): Int {
        return if (articles != null) {
            articles!!.size
        } else {

            0
        }

    }


    class NewsViewHolder
        (itemView: View) : RecyclerView.ViewHolder(itemView) {


        internal var published_date: TextView? = itemView.published
        internal var newsTitle: TextView? = itemView.newsTitle
        internal var img: ImageView? = itemView.thumbnail
        internal var desc: TextView? = itemView.newsInfo
        internal var author: TextView? = itemView.author
        internal var bookmark: TextView? = itemView.bookmark
        internal var cardView: CardView? = itemView.card_view

    }


}