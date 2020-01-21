package com.example.reviewlistapp.device.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule {
    @Provides
    @Singleton
    internal fun provideApplication(application: Application): Context {
        return application
    }

}
