package com.news.it.domain

import android.content.Context
import com.news.it.ModelConverter
import com.news.it.domain.model.ChannelNews
import com.news.it.domain.model.NewsData
import com.news.it.model.RssRoot
import com.news.it.net.ApiServiceBuilder

class DataRepositoryIml(appCtx: Context) : DataRepository {

    private var rssService = ApiServiceBuilder().getRSSService()

    private var dbRepository: DbRepository = DbRepositoryIml(appCtx)

    override suspend fun getNews(): Result<Any> {
        val response = rssService.getData()
        return when {
            response.isSuccessful -> {
                saveNetworkResult(response.body())
                val data = NewsData(ModelConverter.convertToModel(dbRepository.getNews()))
                Result.SuccessPayload(data)
            }
            else -> Result.Error("network error")
        }
    }

    private suspend fun saveNetworkResult(rssRoot: RssRoot?) {
        rssRoot?.let {
            val channel = ModelConverter.convertRssToChannel(rssRoot)
            val channelId = dbRepository.insert(channel)
            val news = ModelConverter.convertRssToEntity(rssRoot, channelId)
            dbRepository.insertNews(news)
        }
    }
}