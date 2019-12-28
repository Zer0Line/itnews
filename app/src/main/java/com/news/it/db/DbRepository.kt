package com.news.it.db

import androidx.lifecycle.LiveData
import com.news.it.db.dao.RssChannelDao
import com.news.it.db.dao.RssNewsDao
import com.news.it.db.entity.RssChannelEntity
import com.news.it.db.entity.RssNewsEntity

class DbRepository(private val rssChannelDao: RssChannelDao, private val rssNewsDao: RssNewsDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val channels: LiveData<List<RssChannelEntity>> = rssChannelDao.getChannels()

    suspend fun insert(channel: RssChannelEntity) {
        rssChannelDao.insert(channel)
    }

    suspend fun insertNews(news: RssNewsEntity) {
        rssNewsDao.insert(news)
    }
}