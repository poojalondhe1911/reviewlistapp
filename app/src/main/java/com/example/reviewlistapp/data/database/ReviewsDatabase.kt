package com.example.reviewlistapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.reviewlistapp.data.database.typeconvertor.AuthorTypeConvertor
import com.example.reviewlistapp.data.database.typeconvertor.ReviewsTypeConverters
import com.example.reviewlistapp.data.database.typeconvertor.PaginationTypeConvertor
import com.example.reviewlistapp.data.datasource.model.*

@Database(
    entities = [Review::class, Reviews::class,Pagination::class, Author::class],
    version = 1
)
@TypeConverters(ReviewsTypeConverters::class,PaginationTypeConvertor::class,AuthorTypeConvertor::class)
abstract class ReviewsDatabase : RoomDatabase(){

    abstract fun getReviewDao(): ReviewDao


    companion object {
        @Volatile private var instance: ReviewsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance
            ?: synchronized(LOCK){
            instance
                ?: buildDatabase(
                    context
                ).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            ReviewsDatabase::class.java, "review_list.db")
            .build()
    }
}
