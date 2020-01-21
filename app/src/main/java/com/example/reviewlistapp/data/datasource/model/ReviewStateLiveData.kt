package com.example.reviewlistapp.data.datasource.model

import androidx.lifecycle.MutableLiveData

class ReviewStateLiveData<T> : MutableLiveData<ReviewStateData<T>>() {

    /**
     * Use this to put the Data on a LOADING Status
     */
    fun postLoading() {
        postValue(ReviewStateData<T>().loading())
    }

    /**
     * Use this to put the Data on a ERROR DataStatus
     * @param throwable the error to be handled
     */
    fun postError(string: String) {
        postValue(ReviewStateData<T>().error(string))
    }

    /**
     * Use this to put the Data on a SUCCESS DataStatus
     * @param data
     */
    fun postSuccess(data: T) {
        postValue(ReviewStateData<T>().success(data))
    }

}
