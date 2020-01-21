package com.example.reviewlistapp.presentation.view.callback

interface OnRecyclerObjectClickListener<in T>  {
    /**
     * Item has been clicked.
     *
     * @param item object associated with the clicked item.
     */
    fun onItemClicked(item: T, position: Int)

    fun onItemLongClicked(item: T, position: Int): Boolean

    fun onRowClicked(item: T, position: Int)
}