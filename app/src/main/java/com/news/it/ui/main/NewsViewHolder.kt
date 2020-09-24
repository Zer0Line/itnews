package com.news.it.ui.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.news.it.domain.model.ChannelNews
import com.news.it.ui.views.NewsState
import com.news.it.ui.views.RssStateView
import kotlinx.android.synthetic.main.news_item_layout.view.*

class NewsViewHolder(view_: View) : RecyclerView.ViewHolder(view_) {
    private val view = view_
    private val titleTV: TextView = view_.newsTitle
    private val descriptionTV: TextView = view_.newsDescription
    private val creatorTV: TextView = view_.newsCreator
    private val rssState: RssStateView = view_.rssState

    fun bind(
        news: ChannelNews
    ) {
        titleTV.text = news.title
        descriptionTV.text = news.imageDesc
        val author = "Автор: ${news.creator}"
        creatorTV.text = author
        rssState.setDate(news.pubDate)
        val read = if (news.read) NewsState.read else NewsState.new
        rssState.setState(read)
    }

    fun setOnClickListener(onClick: () -> Unit) {
        view.setOnClickListener { onClick() }
    }
}