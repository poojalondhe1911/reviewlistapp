package com.example.reviewlistapp.device.di

import com.example.reviewlistapp.presentation.view.ui.reviewlist.ReviewListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeReviewListActivity(): ReviewListActivity
}