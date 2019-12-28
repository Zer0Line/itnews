package com.news.it.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.news.it.db.entity.RssChannelEntity

@Dao
interface RssChannelDao {

    @Query("SELECT * from channel")
    fun getChannels(): LiveData<List<RssChannelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(good: RssChannelEntity)

    @Query("DELETE FROM channel")
    suspend fun deleteAll()
}