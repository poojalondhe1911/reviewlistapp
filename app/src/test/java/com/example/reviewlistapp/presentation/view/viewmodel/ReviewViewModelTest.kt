package com.example.reviewlistapp.presentation.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.reviewlistapp.data.datasource.model.Review
import com.example.reviewlistapp.data.datasource.model.ReviewStateLiveData
import com.example.reviewlistapp.data.datasource.model.Reviews
import com.example.reviewlistapp.domain.contract.IReviewRepo
import com.example.reviewlistapp.domain.usecase.GetInitialReviewList
import com.example.reviewlistapp.domain.usecase.GetReviewsByIdUseCase
import com.example.reviewlistapp.domain.usecase.GetReviewUseCase
import com.example.reviewlistapp.presentation.view.uilivedata.ReviewsListUILiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate


@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(MockitoJUnitRunner::class)
@PrepareForTest(GetReviewUseCase::class, GetReviewsByIdUseCase::class,IReviewRepo::class,GetInitialReviewList::class)
class ReviewViewModelTest {

    private lateinit var viewModel: ReviewListViewModel
    @Mock
    private lateinit var reviewsObserver: Observer<ReviewsListUILiveData<Review>>

    @Mock
    private lateinit var resourceObserver: Observer<ReviewsListUILiveData<Reviews>>

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var  getReviewUseCase: GetReviewUseCase
    @Mock
    lateinit var getReviewsByIdUseCase: GetReviewsByIdUseCase

    @Mock
    lateinit var getInitialReviewList: GetInitialReviewList



    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        val util = TestDatabaseUtil()
        val data = ReviewStateLiveData<Review>()
        data.postSuccess(util.createDummyReview())
        viewModel = ReviewListViewModel(getReviewUseCase,getReviewsByIdUseCase,getInitialReviewList)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun loadReviewDataTest() = runBlocking {
        val data = ReviewsListUILiveData<Review>()
        val util = TestDatabaseUtil()
        data.success(util.createDummyReview())
        val liveData =  MutableLiveData<ReviewsListUILiveData<Review>>()
        liveData.postValue(viewModel.newDataAfterInitalState)
        liveData.observeForever(reviewsObserver)
        delay(10)
        liveData.postValue(data)
        verify(reviewsObserver, timeout(50)).onChanged(data.loading())
        verify(reviewsObserver, timeout(50)).onChanged(data.success(util.createDummyReview()))
    }

    @Test
    fun loadReviewByIdTest() = runBlocking {
        val data = ReviewsListUILiveData<Reviews>()
        val util = TestDatabaseUtil()
        data.success(util.createDummyReview().getReviews()?.first()!!)
        val liveData =  MutableLiveData<ReviewsListUILiveData<Reviews>>()
        liveData.postValue(viewModel.resource)
        liveData.observeForever(resourceObserver)
        delay(10)
        liveData.postValue(data)
        verify(resourceObserver, timeout(50)).onChanged(data.loading())
        verify(resourceObserver, timeout(50)).onChanged(data.success(util.createDummyReview().getReviews()?.first()!!))
    }


}