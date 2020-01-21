package com.example.reviewlistapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.reviewlistapp.data.datasource.model.Review

@Dao
interface ReviewDao {

    @Insert
    fun insert(review: Review)

    @Query("DELETE FROM review_table")
    fun deleteReview()

    @Query("SELECT * FROM review_table ")
    fun getReview(): Review


    @Update
    fun updateReview(review: Review)
}