package com.news.it.domain

sealed class Result<out T : Any> {

    var payload: Any? = null
    var message: String = ""
    var success = true

    constructor(payload: T? = null) {
        this.payload = payload
    }

    constructor(message: String) {
        this.message = message
    }

    class SuccessPayload<T : Any>(payload: T?) : Result<T>(payload)

    class Success : Result<Any>()

    class Error(error: String) : Result<Any>(error) {
        init {
            success = false
        }
    }

    fun isSuccess(): Boolean {
        return success
    }

    companion object {
        val SIMPLE_SUCCESS = Success()
        val ERROR: Result<Any> = Error("Ошибка")
    }
}

