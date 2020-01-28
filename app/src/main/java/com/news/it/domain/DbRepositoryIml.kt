package com.news.it.domain

import android.content.Context
import com.news.it.db.NewsDatabase
import com.news.it.db.entity.RssChannelEntity
import com.news.it.db.entity.RssNewsEntity

class DbRepositoryIml(appCtx: Context) : DbRepository {

    private val channelDao = NewsDatabase.getDatabase(appCtx).channelDao()
    private val newsDao = NewsDatabase.getDatabase(appCtx).newsDao()

    override suspend fun getNews(): List<RssNewsEntity> {
        return newsDao.getNews()
    }

    override suspend fun getChannels(): List<RssChannelEntity> {
        return channelDao.getChannels()
    }

    override suspend fun insert(channel: RssChannelEntity): Long {
        return channelDao.insert(channel)
    }

    override suspend fun insertNews(news: List<RssNewsEntity>) {
        newsDao.insertAll(news)
    }
}