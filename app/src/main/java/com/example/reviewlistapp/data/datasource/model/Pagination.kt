package com.example.reviewlistapp.data.datasource.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by pooja.londhe on ${15/02/18}.
 */

@Entity
class Pagination {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "paginationInfo")
    private var paginationInfo = 1

    @ColumnInfo(name = "offset")
    private var offset: String? = null

    @ColumnInfo(name = "limit")
    private var limit: String? = null

    fun getOffset(): String? {
        return offset
    }

    fun setOffset(offset: String) {
        this.offset = offset
    }

    fun getPaginationInfo(): Int {
        return paginationInfo
    }

    fun setPaginationInfo(paginationInfo: Int) {
        this.paginationInfo = paginationInfo
    }

    fun getLimit(): String? {
        return limit
    }

    fun setLimit(limit: String) {
        this.limit = limit
    }

    override fun toString(): String {
        return "ClassPojo [offset = $offset, limit = $limit, paginationInfo = $paginationInfo]"
    }
}