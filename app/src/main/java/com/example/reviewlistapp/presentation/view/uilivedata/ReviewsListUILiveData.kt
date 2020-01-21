package com.example.reviewlistapp.presentation.view.uilivedata

import androidx.annotation.Nullable


class ReviewsListUILiveData<T> {

    private var status: UIDataStatus?=null

    @Nullable
    private var data: T? = null

    @Nullable
    private var error: String? = null


    fun loading(): ReviewsListUILiveData<T> {
        this.status = UIDataStatus.LOADING
        this.data = null
        this.error = null
        return this
    }

    fun success(data: T): ReviewsListUILiveData<T> {
        this.status = UIDataStatus.SUCCESS
        this.data = data
        this.error = null
        return this
    }

    fun error(error: String): ReviewsListUILiveData<T> {
        this.status = UIDataStatus.ERROR
        this.data = null
        this.error = error
        return this
    }

    fun getStatus(): UIDataStatus? {
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

    enum class UIDataStatus {
        SUCCESS,
        ERROR,
        LOADING,
    }
}