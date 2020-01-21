package com.example.reviewlistapp.presentation.mapper.view.ui

import android.app.Activity
import android.app.Application
import com.example.listtestapplication.device.di.DaggerApplicationComponent
import com.example.reviewlistapp.data.database.IDBProviderFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector:DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    @Inject
    lateinit var appProvider: IDBProviderFactory


    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> {
        return  fragmentInjector
    }


    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)

        appProvider.makeAppInstance()
    }
}