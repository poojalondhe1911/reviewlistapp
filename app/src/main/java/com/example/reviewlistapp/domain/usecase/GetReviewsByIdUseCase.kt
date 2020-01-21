package com.example.reviewlistapp.domain.usecase

import com.example.reviewlistapp.domain.contract.IReviewRepo
import com.example.reviewlistapp.data.datasource.model.ReviewStateLiveData
import com.example.reviewlistapp.data.datasource.model.Reviews
import javax.inject.Inject

class GetReviewsByIdUseCase @Inject constructor (private val repositoryContract: IReviewRepo) {

     fun getReviewsbyId (id:String): ReviewStateLiveData<Reviews> {
        return repositoryContract.getReviewById(id)
    }

}