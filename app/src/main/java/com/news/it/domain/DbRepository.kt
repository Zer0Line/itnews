package com.news.it.domain

import com.news.it.db.entity.RssChannelEntity
import com.news.it.db.entity.RssNewsEntity

interface DbRepository {

    suspend fun getNews(): List<RssNewsEntity>

    suspend fun getChannels(): List<RssChannelEntity>

    suspend fun insert(channel: RssChannelEntity): Long

    suspend fun insertNews(news: List<RssNewsEntity>)

}