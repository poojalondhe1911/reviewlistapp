package com.example.reviewlistapp.data.datasource.remotedatasource.cloud

import com.example.reviewlistapp.data.datasource.model.Review
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface ReviewDataService {

    @GET("activities/23776/reviews")
    fun getReviewList(@QueryMap requestMap: Map<String, Int>, @Header("User-Agent") agent:String): Call<Review>
}