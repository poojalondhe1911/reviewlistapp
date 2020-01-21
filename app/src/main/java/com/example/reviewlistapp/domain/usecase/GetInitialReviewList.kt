package com.example.reviewlistapp.domain.usecase

import com.example.reviewlistapp.data.datasource.model.Review
import com.example.reviewlistapp.data.datasource.model.ReviewStateLiveData
import com.example.reviewlistapp.domain.contract.IReviewRepo
import javax.inject.Inject

/**
 * Created by  on ${15/02/18}.
 */
class GetInitialReviewList  @Inject constructor(private val repositoryContract: IReviewRepo) {

    fun getReviewsData (): ReviewStateLiveData<Review> {
        return repositoryContract. getInitialReviewList()
    }

}



