package com.example.reviewlistapp.data.datasource.remotedatasource.cloud

import android.content.Context
import javax.inject.Inject


class CloudApiClient @Inject constructor(
        val context: Context
): CloudApiClientinterface {

    override fun getJsonService(): ReviewDataService {

        var jsonClient = CloudApiRetrofitClient.getInstance()
        if (jsonClient == null) {
            CloudApiRetrofitClient.makeInstance(
                context
            )
        }
        jsonClient = CloudApiRetrofitClient.getInstance()
                ?: throw IllegalStateException("Retrofit not initialized")
        return jsonClient.create(ReviewDataService::class.java)
    }
}