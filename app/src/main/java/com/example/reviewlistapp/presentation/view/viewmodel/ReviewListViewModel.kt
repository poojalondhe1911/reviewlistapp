package com.example.reviewlistapp.presentation.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.reviewlistapp.data.datasource.model.Reviews
import com.example.reviewlistapp.data.datasource.model.ReviewStateData
import com.example.reviewlistapp.data.datasource.model.Review
import com.example.reviewlistapp.domain.UpdateReviewRequest
import com.example.reviewlistapp.domain.contract.IReviewRepo
import com.example.reviewlistapp.domain.usecase.GetInitialReviewList
import com.example.reviewlistapp.domain.usecase.GetReviewsByIdUseCase
import com.example.reviewlistapp.domain.usecase.GetReviewUseCase
import com.example.reviewlistapp.domain.usecase.UpdateReviewsUseCase
import com.example.reviewlistapp.presentation.view.uilivedata.ReviewsListUILiveData
import javax.inject.Inject

class ReviewListViewModel @Inject constructor(
    private val getReviewUseCase: GetReviewUseCase,
    private val getReviewsByIdUseCase: GetReviewsByIdUseCase,
    private val getInitialReviewListUseCase:GetInitialReviewList
)
    : ViewModel() {

    @Inject
    lateinit var reviewRepo: IReviewRepo

    var resource: ReviewsListUILiveData<Reviews> = ReviewsListUILiveData()

    var newData: ReviewsListUILiveData<Review> = ReviewsListUILiveData()

    var newDataAfterInitalState: ReviewsListUILiveData<Review> = ReviewsListUILiveData()

    var lastKnownPosition : Int = 0

    fun getLastPosition():Int{
        return  lastKnownPosition
    }

    fun getReviewData (): LiveData<ReviewsListUILiveData<Review>>? {
        return Transformations.map(getReviewUseCase.getReviewsData()) {
            when {
                it.getStatus() == ReviewStateData.DataStatus.SUCCESS ->{
                    newDataAfterInitalState.success(it.getData()!!)
                }
                it.getStatus() ==  ReviewStateData.DataStatus.ERROR -> newData.error(it.getError()!!)
                else -> newDataAfterInitalState.loading()
            }
        }
    }
    fun getInitialReviewData(): LiveData<ReviewsListUILiveData<Review>> {
        return Transformations.map(getInitialReviewListUseCase.getReviewsData()) {
            when {
                it.getStatus() == ReviewStateData.DataStatus.SUCCESS ->{
                    lastKnownPosition = it.getData()?.getPagination()?.getLimit()?.toInt()!! - 1
                    newData.success(it.getData()!!)
                }
                it.getStatus() ==  ReviewStateData.DataStatus.ERROR -> newData.error(it.getError()!!)
                else -> newData.loading()
            }
        }
    }

    fun getReviewById(id:String): LiveData<ReviewsListUILiveData<Reviews>> {
        return Transformations.map(getReviewsByIdUseCase.getReviewsbyId(id)) {
            when {
                it.getStatus() == ReviewStateData.DataStatus.SUCCESS ->{
                    resource.success(it.getData()!!)
                }
                it.getStatus() ==  ReviewStateData.DataStatus.ERROR -> resource.error(it.getError()!!)
                else -> resource.loading()
            }
        }
    }

}
