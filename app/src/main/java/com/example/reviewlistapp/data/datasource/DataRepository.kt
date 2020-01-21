package com.example.reviewlistapp.data.datasource

import com.example.reviewlistapp.data.datasource.localdatasource.LocalDataRepository
import com.example.reviewlistapp.data.datasource.model.Review
import com.example.reviewlistapp.data.datasource.model.ReviewStateLiveData
import com.example.reviewlistapp.data.datasource.model.Reviews
import com.example.reviewlistapp.data.datasource.remotedatasource.RemoteDataRespository
import com.example.reviewlistapp.data.datasource.request.ReviewsRequest
import com.example.reviewlistapp.domain.UpdateReviewRequest
import com.example.reviewlistapp.domain.contract.IReviewRepo
import com.example.reviewlistapp.domain.resultmapper.Status
import com.example.reviewlistapp.domain.resultmapper.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DataRepository @Inject constructor(private val localDataRepository: LocalDataRepository, private val remoteDataRespository: RemoteDataRespository) : IReviewRepo{

    override fun  getReviewsList(): ReviewStateLiveData<Review> {
        val reviewLiveDataReview: ReviewStateLiveData<Review> = ReviewStateLiveData()
        GlobalScope.launch {
            val reviewResult = localDataRepository.getReviewsData()
            if (reviewResult?.status == Status.SUCCESS) {
                reviewLiveDataReview.postLoading()
                val oldOffset = reviewResult.data?.getPagination()?.getOffset()?.toInt()?:0
                val oldLimit = reviewResult.data?.getPagination()?.getLimit()?.toInt()?:0
                val offset = oldOffset + oldLimit

                val requestMap = ReviewsRequest().getReviewRequest(
                    reviewResult.data?.getPagination()?.getLimit()?.toInt()?:0,offset)

                val apiReviewResponse = remoteDataRespository.getReviewList(requestMap)
                if (apiReviewResponse?.status == Status.SUCCESS) {
                    val apiData = apiReviewResponse.data!!
                    localDataRepository.updateReviewData(apiData)
                    reviewLiveDataReview.postSuccess(apiData)
                } else {
                    reviewLiveDataReview.postError(apiReviewResponse?.message ?: "")
                }
            }
        }
        return reviewLiveDataReview
    }

    override fun getInitialReviewList(): ReviewStateLiveData<Review> {
        val reviewLiveDataReview: ReviewStateLiveData<Review> = ReviewStateLiveData()
        GlobalScope.launch {
            val reviewResult = localDataRepository.getReviewsData()
            if (reviewResult?.status == Status.SUCCESS) {
                reviewLiveDataReview.postSuccess(reviewResult.data!!)
            } else {
                reviewLiveDataReview.postLoading()
                val requestMap = ReviewsRequest().getReviewRequest(
                    20,0)

                val apiReviewsResponse = remoteDataRespository.getReviewList(requestMap)
                if (apiReviewsResponse?.status == Status.SUCCESS) {
                    localDataRepository.insertReviewsData(apiReviewsResponse.data)
                    reviewLiveDataReview.postSuccess(apiReviewsResponse.data!!)
                } else {
                    reviewLiveDataReview.postError(apiReviewsResponse?.message ?: "")
                }

            }

        }
        return reviewLiveDataReview
    }

    override fun updateReviewData(data: UpdateReviewRequest) {
        GlobalScope.launch {
            localDataRepository.updateReviewData(data.review)
        }
    }

    override fun  getReviewById(id: String): ReviewStateLiveData<Reviews> {
        val reviewLiveDataReview: ReviewStateLiveData<Reviews> = ReviewStateLiveData()
        reviewLiveDataReview.postLoading()
        GlobalScope.launch {
            val reviewsResult: Result<Review>? = localDataRepository.getReviewsData()
            if (reviewsResult?.status == Status.SUCCESS) {
                val resource = reviewsResult.data?.getReviews()?.first { it.getId() == id }
                if (resource != null) {
                    reviewLiveDataReview.postSuccess(resource)
                } else {
                    reviewLiveDataReview.postError("Resource not found")
                }
            }

        }
        return reviewLiveDataReview
    }
}