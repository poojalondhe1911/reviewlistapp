package com.example.reviewlistapp.data.datasource.model

import androidx.annotation.Nullable

class ReviewStateData<T> {
    private var status: DataStatus?=null

    @Nullable
    private var data: T? = null

    @Nullable
    private var error: String? = null

    fun loading(): ReviewStateData<T> {
        this.status = DataStatus.LOADING
        this.data = null
        this.error = null
        return this
    }

    fun success(data: T): ReviewStateData<T> {
        this.status = DataStatus.SUCCESS
        this.data = data
        this.error = null
        return this
    }

    fun error(error: String): ReviewStateData<T> {
        this.status = DataStatus.ERROR
        this.data = null
        this.error = error
        return this
    }

    fun getStatus(): DataStatus? {
        return status
    }

    @Nullable
    fun getData(): T? {
        return data
    }

    @Nullable
    fun getError(): String? {
        return error
    }

    enum class DataStatus {
        SUCCESS,
        ERROR,
        LOADING
    }
}