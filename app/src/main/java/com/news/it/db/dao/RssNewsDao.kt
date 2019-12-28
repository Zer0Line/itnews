package com.news.it.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.news.it.db.entity.RssNewsEntity

@Dao
interface RssNewsDao {

    @Query("SELECT * from news")
    fun getNews(): LiveData<List<RssNewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: RssNewsEntity)

    @Query("DELETE FROM news")
    suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM news")
    fun getFullOrders(): List<RssNewsEntity>
}