package com.task.nytimes.Activities

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.task.nytimes.Models.TopStories
import com.task.nytimes.R
import kotlinx.android.synthetic.main.act_toolbar.*
import kotlinx.android.synthetic.main.detailview_news.*


class News_Detail_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailview_news)
        init()
    }

    fun init() {

        // Toolbar setting
        tvtitle.text = "Story Detail"
        backbtn.setOnClickListener {
            finish()
        }
// getting data from extras
        val data = intent.extras
        val article: TopStories = data.getParcelable<Parcelable>("news") as TopStories
        val position: Int = data.getInt("position")

// checking if the image story exist.
        if (article.results!![position].multimedia!!.size > 0) {
            Picasso.get().load(article.results!![position].multimedia!!.get(0).url).into(thumbnail)

        }
// setting up data for story
        published.setText(
            article.results!![position].published_date +
                    "  |  " + article.results!![position].byline
        )
        newsTitle.setText(article.results!![position].title)


        newsInfo.setText(article.results!![position].abstractdata)
        var content_data: String? = null
        content_data = if (article.results!![position].abstractdata != null) {
            article.results!![position].abstractdata + "\n" + "\nMore at: \n" + article.results!![position].url
        } else {
            """
     Read more about it from : 
     ${article.results!![position].url}
     """.trimIndent()
        }
        content.setText(content_data)
    }
}
