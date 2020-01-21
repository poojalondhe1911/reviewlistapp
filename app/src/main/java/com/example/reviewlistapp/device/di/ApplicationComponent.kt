package com.example.listtestapplication.device.di

import android.app.Application
import com.example.reviewlistapp.device.di.*
import com.example.reviewlistapp.presentation.mapper.view.ui.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ViewModelModule::class,
    FragmentModule::class,
    ActivityModule::class,
    ContextModule::class,
    DBProviderModule::class,
    RepositoryModule::class
])

interface ApplicationComponent {


    /* We will call this builder interface from our custom Application class.
     * This will set our application object to the AppComponent.
     * So inside the AppComponent the application instance is available.
     * So this application instance can be accessed by our modules
     * such as ApiModule when needed
     * */
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }


    /*
     * This is our custom Application class
     * */
    fun inject(appController: MainApplication)
}

