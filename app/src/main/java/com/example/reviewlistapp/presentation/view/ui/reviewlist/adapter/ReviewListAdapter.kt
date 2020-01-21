package com.example.reviewlistapp.presentation.view.ui.reviewlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewlistapp.R
import com.example.reviewlistapp.data.datasource.model.Reviews
import com.example.reviewlistapp.presentation.view.callback.OnRecyclerObjectClickListener
import com.example.reviewlistapp.presentation.view.ui.util.DateConvertorUtil
import com.example.reviewlistapp.presentation.view.ui.util.ReviewDiffCallback
import me.zhanghai.android.materialratingbar.MaterialRatingBar


class ReviewListAdapter : RecyclerView.Adapter<ReviewListAdapter.ItemViewHolder>(){

    lateinit var  listner: OnRecyclerObjectClickListener<Reviews>

    private val reviewsLits = ArrayList<Reviews>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item_review, parent, false)
        return ItemViewHolder(
            listItem
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val myListData = reviewsLits[position]
        if( myListData.getTitle().isNullOrBlank()) {
            holder.txtTitle.visibility = View.GONE
        }
        else {
            holder.txtTitle.visibility = View.VISIBLE
            holder.txtTitle.text = myListData.getTitle()
        }
        if(myListData.getMessage().isNullOrBlank()) {
            holder.txtDesc.visibility = View.GONE
        }
        else {
            holder.txtDesc.visibility = View.VISIBLE
            holder.txtDesc.text = myListData.getMessage()
        }

        holder.txtAuthorName.text = myListData.getAuthor()?.getFullName()?:""
        holder.layoutProfile.text = myListData.getAuthor()?.getFullName()?.get(0).toString()
        holder.textViewDate.text =  DateConvertorUtil.getDate(myListData.getCreated())
        holder.rating.rating = myListData.getRating()?.toFloat()?:0f

        holder.itemView.setOnClickListener{
            listner.onRowClicked(myListData,position)
        }

    }
    override fun getItemCount(): Int {
        return reviewsLits.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle = itemView.findViewById(R.id.textViewTitle) as TextView
        var txtDesc = itemView.findViewById(R.id.txtDescription) as TextView
        var txtAuthorName = itemView.findViewById(R.id.txtAuthorName) as TextView
        var layoutProfile = itemView.findViewById(R.id.layoutProfile) as TextView
        var textViewDate  = itemView.findViewById(R.id.textViewDate) as TextView
        var rating = itemView.findViewById(R.id.rating)  as MaterialRatingBar
    }

    fun setListener(onRecyclerObjectClickListener: OnRecyclerObjectClickListener<Reviews>) {
        listner = onRecyclerObjectClickListener
    }

    fun setData(newReview: Array<Reviews>?): ArrayList<Reviews> {

        val diffCallback = ReviewDiffCallback(reviewsLits, newReview)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        reviewsLits.clear()
        reviewsLits.addAll(newReview!!)
        diffResult.dispatchUpdatesTo(this)
        return reviewsLits
    }

}