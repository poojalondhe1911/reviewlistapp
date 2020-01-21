package com.example.reviewlistapp.data.datasource.request

/**
 * Created by  on ${15/02/18}.
 */
class ReviewsRequest {

    fun getReviewRequest(limit:Int, offset:Int):Map<String,Int>{
       return  mapOf(
            Pair("limit", limit),
            Pair("offset", offset)
        )
    }
}