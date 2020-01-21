package com.example.reviewlistapp.presentation.view.ui.reviewdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.reviewlistapp.R
import com.example.reviewlistapp.data.datasource.model.Reviews
import com.example.reviewlistapp.presentation.view.ui.util.DateConvertorUtil
import com.example.reviewlistapp.presentation.view.ui.util.KeyUtil
import com.example.reviewlistapp.presentation.view.uilivedata.ReviewsListUILiveData
import com.example.reviewlistapp.presentation.view.viewmodel.ReviewListViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_review_details.*
import javax.inject.Inject


class ReviewDetailFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ReviewListViewModel

    companion object {
        fun newInstance() = ReviewDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_review_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(ReviewListViewModel::class.java)
        observeViewModel(viewModel,arguments?.getString(KeyUtil.RESOURCE_ID,"")?:"")
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun observeViewModel(
        viewModel: ReviewListViewModel,
        id: String
    ) {

        viewModel.getReviewById(id).observe(this,
            Observer<ReviewsListUILiveData<Reviews>> { data ->
                when {
                    data.getStatus() == ReviewsListUILiveData.UIDataStatus.SUCCESS -> {
                        displayReviewDetails(data.getData()!!)
                    }
                    data.getStatus() == ReviewsListUILiveData.UIDataStatus.ERROR -> {
                        Toast.makeText(activity,data.getError(), Toast.LENGTH_LONG).show()
                    }
                }
            })

    }
    private fun displayReviewDetails(data: Reviews) {
        if(data.getTitle().isNullOrBlank())
            textViewTitle.visibility = View.GONE
        else
            textViewTitle.text = data.getTitle()
        if(data.getEnjoyment().isNullOrBlank())
            txtEnjoymentDesc.text = getString(R.string.no_information)
        else
            txtEnjoymentDesc.text = data.getEnjoyment()
        if(data.getTravelerType().isNullOrBlank())
            txtTravleTypeDesc.text = getString(R.string.no_information)
        else
            txtTravleTypeDesc.text = data.getTravelerType()
        if(data.getMessage().isNullOrBlank())
            txtDescription.visibility = View.GONE
        else
            txtDescription.text = data.getMessage()

        textViewTitle.text = data.getTitle()
        txtAuthorName.text = data.getAuthor()?.getFullName()
        layoutProfile.text = data.getAuthor()?.getFullName()?.get(0)?.toString()
        textViewDate.text = DateConvertorUtil.getDate(data.getCreated())
        rating.rating = data.getRating()?.toFloat()?:0f
    }
}
