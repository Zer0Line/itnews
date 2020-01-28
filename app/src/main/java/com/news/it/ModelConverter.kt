package com.news.it

import com.news.it.db.entity.RssChannelEntity
import com.news.it.db.entity.RssNewsEntity
import com.news.it.domain.model.ChannelNews
import com.news.it.model.RssRoot

object ModelConverter {

    fun convertRssToChannel(rss: RssRoot): RssChannelEntity {
        val c = rss.channel
        return RssChannelEntity(
            title = c?.title ?: "",
            description = c?.description ?: "",
            link = c?._link ?: ""
        )
    }

    fun convertRssToEntity(rss: RssRoot, channelId: Long): List<RssNewsEntity> {
        return rss.channel?.rssItem?.map { news ->
            RssNewsEntity(
                channelId = channelId,
                description = news.description,
                title = news.title ?: "",
                creator = news.creator ?: "",
                imageDesc = news.imageDesc,
                imageLink = news.imageLink,
                link = news.link,
                pubDate = news.pubDate,
                read = false
            )
        } ?: listOf()
    }

    fun convertToModel(entities: List<RssNewsEntity>): List<ChannelNews> {
        return entities.map { entity ->
            ChannelNews(
                entity.title,
                entity.description,
                entity.pubDate,
                entity.link,
                entity.creator,
                entity.imageLink,
                entity.imageDesc,
                entity.read
            )
        }
    }
}