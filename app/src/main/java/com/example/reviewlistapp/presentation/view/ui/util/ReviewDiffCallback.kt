package com.example.reviewlistapp.presentation.view.ui.util

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.example.reviewlistapp.data.datasource.model.Reviews

/**
 * Created by  on ${15/02/18}.
 */
class ReviewDiffCallback(private val oldList: ArrayList<Reviews>, private val newList: Array<Reviews>?) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList?.size?:0

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).getId() == newList?.get(newItemPosition)?.getId()
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val  id = oldList[oldPosition]
        val id1 = newList?.get(newPosition)

        return id.getId() == id1?.getId()
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}