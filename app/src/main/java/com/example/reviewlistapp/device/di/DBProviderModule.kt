package com.example.reviewlistapp.device.di

import android.app.Application
import com.example.reviewlistapp.data.database.DbProviderFactoryImpl
import com.example.reviewlistapp.data.database.IDBProviderFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBProviderModule {

    @Provides
    @Singleton
    internal fun provideDBProvider(application: Application): IDBProviderFactory {
        return DbProviderFactoryImpl(application)
    }
}