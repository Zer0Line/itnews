package com.news.it.db.dao

import androidx.room.*
import com.news.it.db.entity.RssNewsEntity

@Dao
interface RssNewsDao {

    @Query("SELECT * from news")
    suspend fun getNews(): List<RssNewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: RssNewsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<RssNewsEntity>)

    @Query("DELETE FROM news")
    suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM news")
    fun getFullOrders(): List<RssNewsEntity>
}