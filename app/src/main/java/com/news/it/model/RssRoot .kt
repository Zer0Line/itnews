package com.news.it.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.databind.JsonNode
import java.util.*
import kotlin.collections.LinkedHashMap
import kotlin.random.Random

data class RssRoot(
    @JsonProperty("channel")
    val channel: Channel?
)

data class Channel(
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("item")
    val rssItem: List<NewsItem>?
) {

    var _link: String = ""
    var linkData: LinkedHashMap<*, *>? = null

    @JsonProperty("link")
    var link: Any? = null
        set(value) {
            when (value) {
                is String -> _link = value
                is LinkedHashMap<*, *> -> linkData = value
            }
            field = value
        }
}

data class NewsItem(
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("pubDate")
    val pubDate: Date?,
    @JsonProperty("link")
    val link: String?,
    @JsonProperty("dc")
    val creator: String?,
    @JsonProperty("guid")
    val _guid: LinkedHashMap<String, String>?

) {
    var imageLink: String = ""
    var imageDesc: String = ""
    var guid: String = ""

    init {
        val split: List<String>? = description?.split("\n")
        imageDesc = split?.get(0)?.trim() ?: ""
        imageLink = split?.get(1)?.
            substringAfter("src=&quot;")?.
            substringBefore("&quot;")?.trim() ?: ""
        guid = _guid?.values?.last() ?: ""
    }
}