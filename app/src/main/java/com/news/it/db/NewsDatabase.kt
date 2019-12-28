package com.news.it.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.news.it.db.dao.RssChannelDao
import com.news.it.db.dao.RssNewsDao
import com.news.it.db.entity.RssChannelEntity
import com.news.it.db.entity.RssNewsEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [RssChannelEntity::class, RssNewsEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun channelDao(): RssChannelDao
    abstract fun newsDao(): RssNewsDao

    private class CRDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val newsDao: RssNewsDao = database.newsDao()

                    // Delete all content here.
                    //orderDao.deleteAll()
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): NewsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "mrmk_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(CRDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}