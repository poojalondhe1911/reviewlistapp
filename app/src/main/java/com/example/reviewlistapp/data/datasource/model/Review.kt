package com.example.reviewlistapp.data.datasource.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by pooja.londhe on ${15/02/18}.
 */

@Entity(tableName = "review_table")
class Review {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "reviewId")
    private var reviewId: Int = 0

    @ColumnInfo(name = "pagination")
    private var pagination: Pagination? = null

    @ColumnInfo(name = "reviews")
    private var reviews: Array<Reviews>? = null

    @ColumnInfo(name = "averageRating")
    private var averageRating: String? = null

    @ColumnInfo(name = "totalCount")
    private var totalCount: String? = null

    fun getReviewId():Int{
        return reviewId
    }
    fun setReviewId(reviewId:Int){
        this.reviewId = reviewId
    }

    fun getPagination(): Pagination? {
        return pagination
    }

    fun setPagination(pagination: Pagination) {
        this.pagination = pagination
    }

    fun getReviews(): Array<Reviews>? {
        return reviews
    }

    fun setReviews(reviews: Array<Reviews>) {
        this.reviews = reviews
    }

    fun getAverageRating(): String? {
        return averageRating
    }

    fun setAverageRating(averageRating: String) {
        this.averageRating = averageRating
    }

    fun getTotalCount(): String? {
        return totalCount
    }

    fun setTotalCount(totalCount: String) {
        this.totalCount = totalCount
    }

    override fun toString(): String {
        return "ClassPojo [pagination = $pagination, reviews = $reviews, averageRating = $averageRating, totalCount = $totalCount, reviewId = $reviewId]"
    }
}