package com.news.it.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "news", foreignKeys = [ForeignKey(
        entity = RssChannelEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("channelId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class RssNewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "channelId")
    val channelId: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "pubDate")
    val pubDate: Date?,
    @ColumnInfo(name = "link")
    val link: String?,
    @ColumnInfo(name = "creator")
    val creator: String?,
    @ColumnInfo(name = "imageLink")
    val imageLink: String?,
    @ColumnInfo(name = "imageDesc")
    val imageDesc: String?,
    @ColumnInfo(name = "read")
    val read: Boolean?
)