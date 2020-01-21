package com.example.reviewlistapp.presentation.view.ui.reviewdetails

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.reviewlistapp.R
import com.example.reviewlistapp.presentation.idelingresource.EspressoIdlingResouce
import com.example.reviewlistapp.presentation.view.ui.reviewlist.ReviewListActivity
import junit.framework.Assert.assertNotSame
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReviewListFragmentTest{

    @get:Rule
    var mainActivityActivityTestRule = ActivityTestRule(ReviewListActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResouce.getIdlingResource())
        val intent = Intent()
        mainActivityActivityTestRule.launchActivity(intent)
    }
    @Test
    fun checkIfListLoaded(){
        val recyclerView: RecyclerView = mainActivityActivityTestRule.activity.findViewById(R.id.recyclerViewReview)
        onView(withId(R.id.recyclerViewReview)).check(
            matches(
                isDisplayed()
            )
        )
        assertNotSame(0, recyclerView.adapter?.itemCount)
    }
    @Test
    fun performReviewItemListClick(){
        onView(withId(R.id.recyclerViewReview)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withText("Review Details")).check(matches(isDisplayed()))
    }
    @After
    fun destroy() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResouce.getIdlingResource())
    }

}