package com.example.reviewlistapp.domain.usecase

import com.example.reviewlistapp.data.datasource.model.ReviewStateLiveData
import com.example.reviewlistapp.data.datasource.model.Review
import com.example.reviewlistapp.domain.contract.IReviewRepo
import javax.inject.Inject

class GetReviewUseCase @Inject constructor(private val repositoryContract: IReviewRepo) {

      fun getReviewsData (): ReviewStateLiveData<Review> {
        return repositoryContract.getReviewsList()
    }

}