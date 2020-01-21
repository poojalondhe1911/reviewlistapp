package com.example.reviewlistapp.data.database

import com.example.reviewlistapp.data.datasource.model.Author
import com.example.reviewlistapp.data.datasource.model.Pagination
import com.example.reviewlistapp.data.datasource.model.Review
import com.example.reviewlistapp.data.datasource.model.Reviews

class TestDatabaseUtil {
    fun createDummyReview(): Review {

        val pagination = Pagination()
        pagination.setOffset("5");
        pagination.setLimit("5");

        val review = Review()
        val firstReview = Reviews()
        val listOfReviews = arrayListOf<Reviews>()
        val authorForReview = Author()
        authorForReview.setAuthorId(1);
        authorForReview.setCountry("India")
        authorForReview.setFullName("XYZ")

        firstReview.setAuthor(authorForReview)
        firstReview.setId("1")
        firstReview.setCreated("")
        firstReview.setEnjoyment("")
        firstReview.setIsAnonymous("true")
        firstReview.setMessage("amazing trip")
        firstReview.setTravelerType("solo")
        firstReview.setLanguage("english")

        listOfReviews.add(firstReview)

        review.setReviews(listOfReviews.toTypedArray())
        review.setAverageRating("2.5")
        review.setReviewId(124)
        review.setTotalCount("20")
        review.setPagination(pagination)

        return review
    }
}