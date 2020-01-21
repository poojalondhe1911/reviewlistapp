package com.example.reviewlistapp.data.datasource.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by pooja.londhe on ${15/02/18}.
 */
@Entity
class Author {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "authorId")
    private var authorId: Int = 1

    @ColumnInfo(name = "country")
    private var country: String? = null

    @ColumnInfo(name = "fullName")
    private var fullName: String? = null

    fun setAuthorId(authorId: Int){
        this.authorId = authorId
    }

    fun getAuthorId():Int{
        return authorId
    }
    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String) {
        this.country = country
    }

    fun getFullName(): String? {
        return fullName
    }

    fun setFullName(fullName: String) {
        this.fullName = fullName
    }

    override fun toString(): String {
        return "ClassPojo [country = $country, fullName = $fullName]"
    }
}