package com.news.it.db

import androidx.lifecycle.LiveData
import com.news.it.db.dao.RssChannelDao
import com.news.it.db.dao.RssNewsDao
import com.news.it.db.entity.RssChannelEntity
import com.news.it.db.entity.RssNewsEntity

class DbRepository(private val rssChannelDao: RssChannelDao, private val rssNewsDao: RssNewsDao) {

    val channels: LiveData<List<RssChannelEntity>> = rssChannelDao.getChannels()

    suspend fun getNews(): List<RssNewsEntity> {
        return rssNewsDao.getNews()
    }

    suspend fun insert(channel: RssChannelEntity): Long {
        return rssChannelDao.insert(channel)
    }

    suspend fun insertNews(news: List<RssNewsEntity>) {
        rssNewsDao.insertAll(news)
    }
}