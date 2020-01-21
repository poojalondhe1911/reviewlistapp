package com.example.reviewlistapp.data.database.typeconvertor

import androidx.room.TypeConverter
import com.example.reviewlistapp.data.datasource.model.Pagination
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class PaginationTypeConvertor {
    @TypeConverter
    fun fromString(value: String): Pagination {
        val listType = object : TypeToken<Pagination>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayLisr(pagination: Pagination): String {
        val gson = Gson()
        return gson.toJson(pagination)
    }

}