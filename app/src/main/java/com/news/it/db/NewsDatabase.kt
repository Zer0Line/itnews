package com.news.it.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.news.it.db.dao.RssChannelDao
import com.news.it.db.dao.RssNewsDao
import com.news.it.db.entity.RssChannelEntity
import com.news.it.db.entity.RssNewsEntity

@Database(
    entities = [RssChannelEntity::class, RssNewsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun channelDao(): RssChannelDao
    abstract fun newsDao(): RssNewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getDatabase(
            context: Context
        ): NewsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}