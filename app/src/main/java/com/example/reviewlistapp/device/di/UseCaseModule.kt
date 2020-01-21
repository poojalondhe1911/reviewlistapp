package com.example.reviewlistapp.device.di

import com.example.reviewlistapp.domain.contract.IReviewRepo
import com.example.reviewlistapp.domain.usecase.GetReviewUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideUseCase(repositoryContract: IReviewRepo): GetReviewUseCase {
        return GetReviewUseCase(repositoryContract)
    }
}