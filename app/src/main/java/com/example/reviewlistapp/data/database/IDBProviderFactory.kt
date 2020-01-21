package com.example.reviewlistapp.data.database

interface IDBProviderFactory {
     fun getAppDatabase(): ReviewsDatabase
     fun makeAppInstance()
}