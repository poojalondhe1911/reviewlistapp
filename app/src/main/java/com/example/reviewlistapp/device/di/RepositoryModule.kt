package com.example.listtestapplication.device.di

import android.content.Context
import com.example.reviewlistapp.domain.resultmapper.ResultHandler
import com.example.reviewlistapp.data.database.IDBProviderFactory
import com.example.reviewlistapp.data.datasource.DataRepository
import com.example.reviewlistapp.data.datasource.localdatasource.LocalDataRepository
import com.example.reviewlistapp.data.datasource.remotedatasource.RemoteDataRespository
import com.example.reviewlistapp.data.datasource.remotedatasource.cloud.CloudApiClient
import com.example.reviewlistapp.data.datasource.remotedatasource.cloud.CloudApiClientinterface
import com.example.reviewlistapp.domain.contract.IReviewRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideClientApiCliente(context: Context): CloudApiClientinterface {
        return CloudApiClient(context)
    }

    @Provides
    @Singleton
    fun provideRepo(repo: DataRepository): IReviewRepo {
        return repo
    }

    @Singleton
    @Provides
    fun provideLocalRepository(responseHandler: ResultHandler, dbProviderFactoryImpl: IDBProviderFactory): LocalDataRepository {
        return LocalDataRepository(responseHandler,dbProviderFactoryImpl)
    }

    @Singleton
    @Provides
    fun provideRemoteRepository(clientService: CloudApiClient, responseHandler: ResultHandler): RemoteDataRespository {
        return RemoteDataRespository(clientService,responseHandler)
    }

    @Singleton
    @Provides
    fun provideResultHandler(): ResultHandler {
        return ResultHandler()
    }

}