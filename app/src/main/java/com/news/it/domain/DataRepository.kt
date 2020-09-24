package com.news.it.domain

interface DataRepository {
    suspend fun getNews(): Result<Any>
}