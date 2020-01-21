package com.example.reviewlistapp.data.database.typeconvertor

import androidx.room.TypeConverter
import com.example.reviewlistapp.data.datasource.model.Author
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by  on ${15/02/18}.
 */
class AuthorTypeConvertor {
    @TypeConverter
    fun fromString(value: String): Author {
        val listType = object : TypeToken<Author>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayLisr(pagination: Author): String {
        val gson = Gson()
        return gson.toJson(pagination)
    }
}