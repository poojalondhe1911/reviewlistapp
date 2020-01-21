package com.example.reviewlistapp.data.datasource.remotedatasource

import com.example.reviewlistapp.domain.resultmapper.Result
import com.example.reviewlistapp.domain.resultmapper.ResultHandler
import com.example.reviewlistapp.data.datasource.model.Review
import com.example.reviewlistapp.data.datasource.remotedatasource.cloud.CloudApiClient
import com.example.reviewlistapp.data.datasource.remotedatasource.cloud.ReviewDataService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataRespository @Inject constructor(private val clientService : CloudApiClient,
                                                private val resultHandler: ResultHandler
) {

    private fun getApi(): ReviewDataService {
        return clientService.getJsonService()
    }
    suspend fun getReviewList(data:Map<String,Int>) :  Result<Review>?{
        return try {
            var reviewData:Review?=null
            withContext(Dispatchers.IO) {
                reviewData = getApi().getReviewList(data,"GetYourGuide").execute().body()
            }
            resultHandler.handleSuccess(reviewData!!)
        } catch (e: Exception) {
            resultHandler.handleException<Review>(e)
        }
    }
}