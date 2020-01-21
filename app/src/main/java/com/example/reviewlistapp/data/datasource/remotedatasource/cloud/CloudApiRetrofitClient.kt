package com.example.reviewlistapp.data.datasource.remotedatasource.cloud

import android.content.Context
import com.example.reviewlistapp.data.datasource.remotedatasource.cloud.util.APIDetailsUtil
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CloudApiRetrofitClient {
    var retrofit: Retrofit? = null
    var HTTPS_API_REVIEW_URL = APIDetailsUtil.API_URL
    fun getInstance(): Retrofit? {
        synchronized(CloudApiRetrofitClient::class) {
            return retrofit
        }
    }

    fun makeInstance(context: Context) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val cacheSize = 10 * 1024 * 1024L // 10 MB
        val cache = Cache(context.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(logging)
            .build()

        val factory = GsonConverterFactory.create()

        synchronized(CloudApiRetrofitClient::class) {
            retrofit = Retrofit.Builder()
                .baseUrl(HTTPS_API_REVIEW_URL)
                .addConverterFactory(factory)
                .client(okHttpClient)
                .build()
        }
    }
}