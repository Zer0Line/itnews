package com.news.it.model

sealed class Result<out T : Any> {

    var success: Boolean = true

    data class Success<out T : Any>(
        val payload: T? = null
    ) : Result<T>()

    data class Error(
        val error: String
    ) : Result<Any>(){
        init {
            success = false
        }
    }

    companion object {
        val SIMPLE_SUCCESS: Result<Any> = Success()
        val ERROR: Result<Any> = Error("Ошибка")
    }
}