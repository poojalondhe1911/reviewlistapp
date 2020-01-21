package com.example.reviewlistapp.device.di

import com.example.reviewlistapp.presentation.view.ui.reviewdetails.ReviewDetailFragment
import com.example.reviewlistapp.presentation.view.ui.reviewlist.ReviewListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeReviewListFragment(): ReviewListFragment

    @ContributesAndroidInjector
    abstract fun contributeReviewDetailFragment(): ReviewDetailFragment

}