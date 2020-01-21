package com.example.reviewlistapp.presentation.view.ui.reviewlist

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.reviewlistapp.R
import com.example.reviewlistapp.data.datasource.model.Review
import com.example.reviewlistapp.data.datasource.model.Reviews
import com.example.reviewlistapp.presentation.idelingresource.EspressoIdlingResouce
import com.example.reviewlistapp.presentation.view.callback.OnRecyclerObjectClickListener
import com.example.reviewlistapp.presentation.view.ui.reviewlist.adapter.ReviewListAdapter
import com.example.reviewlistapp.presentation.view.uilivedata.ReviewsListUILiveData
import com.example.reviewlistapp.presentation.view.viewmodel.ReviewListViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.review_list_fragment.*
import javax.inject.Inject


class ReviewListFragment : Fragment() {

    companion object {
        fun newInstance() = ReviewListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ReviewListViewModel

    private lateinit var reviewListAdapter: ReviewListAdapter

    lateinit var fragmentaction: IOnFragmentItemAction

     var reviewsData = ArrayList<Reviews>()
    var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.review_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }
    private fun initRecyclerView() {
        val mLayoutManager = LinearLayoutManager(activity)
        recyclerViewReview.layoutManager = mLayoutManager
        reviewListAdapter = ReviewListAdapter()
        recyclerViewReview.adapter  = reviewListAdapter
        (recyclerViewReview.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        recyclerViewReview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == reviewsData.size - 1){
                        loadMore()
                       isLoading = true
                    }
                }
            }
        })
        reviewListAdapter.setListener(object :
            OnRecyclerObjectClickListener<Reviews> {
            override fun onItemClicked(item: Reviews, position: Int) {
            }
            override fun onItemLongClicked(
                item: Reviews,
                position: Int
            ): Boolean {
                return false
            }

            override fun onRowClicked(item: Reviews, position: Int) {
                fragmentaction.openDetailsActivity(
                    item.getId()
                )
            }
        })
    }
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        this.fragmentaction = activity as IOnFragmentItemAction
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=  ViewModelProviders.of(this, viewModelFactory)
            .get(ReviewListViewModel::class.java)
        observeViewModel(viewModel)

    }

    private fun observeViewModel(viewModel: ReviewListViewModel) {

        viewModel.getInitialReviewData().observe(this,
            Observer<ReviewsListUILiveData<Review>> { data ->
                when {
                    data.getStatus() == ReviewsListUILiveData.UIDataStatus.SUCCESS -> {
                        EspressoIdlingResouce.decrement()
                        pbHeaderProgress.visibility = View.GONE
                        initialDataLoad(data)
                    }
                    data.getStatus() == ReviewsListUILiveData.UIDataStatus.ERROR -> {
                        EspressoIdlingResouce.decrement()
                        pbHeaderProgress.visibility = View.GONE
                        Toast.makeText(activity, data.getError(), Toast.LENGTH_LONG)
                            .show()
                    }
                    data.getStatus() == ReviewsListUILiveData.UIDataStatus.LOADING -> {
                        EspressoIdlingResouce.increment()
                        pbHeaderProgress.visibility = View.VISIBLE
                    }
                }
            })

    }

    private fun showReview(data: ReviewsListUILiveData<Review>?) {
        val changedReview = reviewListAdapter.setData(data?.getData()?.getReviews())
        reviewsData.clear()
        reviewsData.addAll(changedReview)
    }

    private fun setDataToAdapter(){
        reviewListAdapter.setData(reviewsData.toTypedArray())
        reviewListAdapter.notifyDataSetChanged()
    }

    private fun initialDataLoad(data: ReviewsListUILiveData<Review>?){
        reviewsData.clear()
        reviewsData.addAll(data?.getData()?.getReviews()?: emptyArray())
        setDataToAdapter()
    }
    private fun loadMore() {
        viewModel.getReviewData()?.observe(this,
            Observer<ReviewsListUILiveData<Review>> { data ->
                when {
                    data.getStatus() == ReviewsListUILiveData.UIDataStatus.SUCCESS -> {
                        EspressoIdlingResouce.decrement()
                        showReview(data)
                        isLoading = false
                        pbHeaderProgress.visibility = View.GONE
                    }
                    data.getStatus() == ReviewsListUILiveData.UIDataStatus.ERROR -> {
                        EspressoIdlingResouce.decrement()
                        pbHeaderProgress.visibility = View.GONE
                    }
                    data.getStatus() == ReviewsListUILiveData.UIDataStatus.LOADING -> {
                        EspressoIdlingResouce.increment()
                        pbHeaderProgress.visibility = View.VISIBLE
                    }
                }
            })
    }


    interface IOnFragmentItemAction{
        fun openDetailsActivity(reviewId: String?)
    }

}



