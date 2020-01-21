package com.example.reviewlistapp.domain.usecase

import com.example.reviewlistapp.domain.UpdateReviewRequest
import com.example.reviewlistapp.domain.contract.IReviewRepo
import javax.inject.Inject

/**
 * Created by  on ${15/02/18}.
 */
class UpdateReviewsUseCase @Inject constructor (private val repositoryContract: IReviewRepo) {

    fun updateReview (data: UpdateReviewRequest) {
        return repositoryContract.updateReviewData(data)
    }

}
