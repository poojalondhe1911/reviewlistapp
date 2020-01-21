package com.example.reviewlistapp.data.datasource.localdatasource

import com.example.reviewlistapp.data.database.IDBProviderFactory
import com.example.reviewlistapp.data.datasource.model.Review
import com.example.reviewlistapp.data.datasource.model.Reviews
import com.example.reviewlistapp.domain.resultmapper.Result
import com.example.reviewlistapp.domain.resultmapper.ResultHandler
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LocalDataRepository @Inject constructor(
    private val responseHandler: ResultHandler,
    private val dbProviderFactoryImpl: IDBProviderFactory
) {

    suspend fun getReviewsData(): Result<Review>? {
        return try {
            var review: Review? = null
            withContext(IO) {
                review = dbProviderFactoryImpl.getAppDatabase().getReviewDao().getReview()
            }
            responseHandler.handleSuccess(review!!)
        } catch (e: Exception) {
            responseHandler.handleException<Review>(e)
        }
    }

    suspend fun insertReviewsData(data: Review?): Result<Review>? {
        return try {
            withContext(IO) {
                dbProviderFactoryImpl.getAppDatabase().getReviewDao().insert(review = data!!)
            }
            responseHandler.handleSuccess(data!!)
        } catch (e: Exception) {
            responseHandler.handleException<Review>(e)
        }
    }

    suspend fun updateReviewData(data: Review?): Result<Review>? {
        return try {
            withContext(IO) {
                val review = dbProviderFactoryImpl.getAppDatabase().getReviewDao().getReview().getReviews()
                val updatedReview: ArrayList<Reviews> = ArrayList()
                updatedReview.addAll(review!!)
                updatedReview.addAll(data?.getReviews()!!)
                data.setReviews(updatedReview.toTypedArray())
                dbProviderFactoryImpl.getAppDatabase().getReviewDao().updateReview(data)
            }
            responseHandler.handleSuccess(data!!)
        } catch (e: Exception) {
            responseHandler.handleException<Review>(e)
        }

    }

}

