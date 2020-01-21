package com.example.reviewlistapp.data.database

import android.content.Context
import javax.inject.Inject

class DbProviderFactoryImpl @Inject constructor(private val mContext: Context): IDBProviderFactory {

    companion object {
        private var appDbInstance : ReviewsDatabase? = null
    }
    /**
     * Database instance should be made only once from application
     */
    override fun makeAppInstance() {
        appDbInstance =
            ReviewsDatabase.invoke(
                mContext
            )
    }

    override fun getAppDatabase(): ReviewsDatabase {
        return appDbInstance
            ?: throw IllegalStateException("You must call DbProviderFactory.makeInstance() in Application.onCreate()")
    }

}