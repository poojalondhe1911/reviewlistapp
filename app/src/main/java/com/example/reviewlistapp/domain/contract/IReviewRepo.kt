package com.example.reviewlistapp.domain.contract

import com.example.reviewlistapp.data.datasource.model.Review
import com.example.reviewlistapp.data.datasource.model.ReviewStateLiveData
import com.example.reviewlistapp.data.datasource.model.Reviews
import com.example.reviewlistapp.domain.UpdateReviewRequest

interface IReviewRepo {

    fun getReviewsList(): ReviewStateLiveData<Review>
    fun getReviewById(id:String) : ReviewStateLiveData<Reviews>
    fun getInitialReviewList(): ReviewStateLiveData<Review>
    fun updateReviewData(data: UpdateReviewRequest)
}