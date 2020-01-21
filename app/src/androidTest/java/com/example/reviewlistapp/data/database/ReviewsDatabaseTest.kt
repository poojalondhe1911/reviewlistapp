package com.example.reviewlistapp.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
@LargeTest
class ReviewsDatabaseTest {
    private var reviewDao: ReviewDao? = null
    private var db: ReviewsDatabase? = null

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ReviewsDatabase::class.java).build()
        reviewDao = db!!.getReviewDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db?.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeReviewAndReadTest() {
        val util = TestDatabaseUtil()
        val review = util.createDummyReview()
        reviewDao?.insert(review)
        val dbreview = reviewDao?.getReview()
        assertNotNull(dbreview)
    }
    @Test
    @Throws(Exception::class)
    fun updateReviewAndReadTest() {
        val util = TestDatabaseUtil()
        val review = util.createDummyReview()
        reviewDao?.insert(review)
        val dbreview = reviewDao?.getReview()
        val dbreviewUpdated = reviewDao?.updateReview(dbreview!!)
        assertNotNull(dbreviewUpdated)
    }
}