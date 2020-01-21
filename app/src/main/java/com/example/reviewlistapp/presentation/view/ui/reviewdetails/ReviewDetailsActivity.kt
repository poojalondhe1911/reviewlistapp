package com.example.reviewlistapp.presentation.view.ui.reviewdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reviewlistapp.R
import com.example.reviewlistapp.presentation.view.ui.util.FrgmentUtil.replaceFragment
import com.example.reviewlistapp.presentation.view.ui.util.KeyUtil


class ReviewDetailsActivity : AppCompatActivity(){

    private lateinit var detailsFragment:ReviewDetailFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_details)
        val bundle = Bundle()
        bundle.putString(KeyUtil.RESOURCE_ID, intent.getStringExtra(KeyUtil.RESOURCE_ID))
        detailsFragment = ReviewDetailFragment.newInstance()
        detailsFragment.arguments = bundle
        replaceFragment(detailsFragment, KeyUtil.DETAILS_FRAGMENT_TAG,containerViewId = R.id.framelayout_review)
    }
}
