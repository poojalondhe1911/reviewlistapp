package com.example.reviewlistapp.presentation.view.ui.reviewlist

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reviewlistapp.R
import com.example.reviewlistapp.presentation.view.ui.util.FrgmentUtil.replaceFragment
import com.example.reviewlistapp.presentation.view.ui.util.KeyUtil
import com.example.reviewlistapp.presentation.view.ui.reviewdetails.ReviewDetailsActivity
import dagger.android.AndroidInjection


class ReviewListActivity : AppCompatActivity(), ReviewListFragment.IOnFragmentItemAction {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        replaceFragment(ReviewListFragment.newInstance(),KeyUtil.LIST_FRAGMENT_TAG,containerViewId = R.id.framelayout_review)

    }
    override fun openDetailsActivity(reviewId: String?) {

        val intent = Intent(this, ReviewDetailsActivity::class.java)
        intent.putExtra(KeyUtil.RESOURCE_ID, reviewId)
        val options = ActivityOptions.makeSceneTransitionAnimation(this)
        startActivity(intent, options.toBundle())
    }
}
