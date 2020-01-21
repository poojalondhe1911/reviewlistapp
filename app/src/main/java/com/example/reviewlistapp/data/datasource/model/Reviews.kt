package com.example.reviewlistapp.data.datasource.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by pooja.londhe on ${15/02/18}.
 */

@Entity(tableName = "reviews_table")
class Reviews {

    @ColumnInfo(name = "enjoyment")
    private var enjoyment: String? = null

    @ColumnInfo(name = "isAnonymous")
    private var isAnonymous: String? = null

    @ColumnInfo(name = "travelerType")
    private var travelerType: String? = null

    @ColumnInfo(name = "author")
    private var author: Author? = null

    @ColumnInfo(name = "created")
    private var created: String? = null

    @ColumnInfo(name = "rating")
    private var rating: String? = null

    @ColumnInfo(name = "language")
    private var language: String? = null

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private var id: String = ""

    @ColumnInfo(name = "title")
    private var title: String? = null

    @ColumnInfo(name = "message")
    private var message: String? = null

    fun getEnjoyment(): String? {
        return enjoyment
    }

    fun setEnjoyment(enjoyment: String) {
        this.enjoyment = enjoyment
    }

    fun getIsAnonymous(): String? {
        return isAnonymous
    }

    fun setIsAnonymous(isAnonymous: String) {
        this.isAnonymous = isAnonymous
    }

    fun getTravelerType(): String? {
        return travelerType
    }

    fun setTravelerType(travelerType: String) {
        this.travelerType = travelerType
    }

    fun getAuthor(): Author? {
        return author
    }

    fun setAuthor(author: Author) {
        this.author = author
    }

    fun getCreated(): String? {
        return created
    }

    fun setCreated(created: String) {
        this.created = created
    }

    fun getRating(): String? {
        return rating
    }

    fun setRating(rating: String) {
        this.rating = rating
    }

    fun getLanguage(): String? {
        return language
    }

    fun setLanguage(language: String) {
        this.language = language
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    override fun toString(): String {
        return "ClassPojo [enjoyment = $enjoyment, isAnonymous = $isAnonymous, travelerType = $travelerType, author = $author, created = $created, rating = $rating, language = $language, id = $id, title = $title, message = $message]"
    }
}