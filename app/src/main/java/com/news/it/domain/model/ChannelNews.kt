package com.news.it.domain.model

import java.util.*

data class ChannelNews(
    val title: String?,
    val description: String?,
    val pubDate: Date?,
    val link: String?,
    val creator: String?,
    var imageLink: String?,
    var imageDesc: String?,
    val read: Boolean = false
)