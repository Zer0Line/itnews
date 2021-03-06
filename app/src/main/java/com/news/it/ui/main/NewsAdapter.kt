package com.news.it.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.news.it.R
import com.news.it.domain.model.ChannelNews

class NewsAdapter(
    private val context: Context?,
    private val onNewsClick: (Int?) -> Unit
) : RecyclerView.Adapter<NewsViewHolder>() {

    private var items: List<ChannelNews> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.news_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position])
        holder.setOnClickListener { onNewsClick(position) }
    }

    fun updateData(models: List<ChannelNews>?) {
        items = models ?: listOf()
        notifyDataSetChanged()
    }

    fun getItem(pos: Int): ChannelNews {
        return items[pos]
    }

    override fun getItemCount(): Int {
        return items.size
    }
}