package com.example.reviewlistapp.data.database.typeconvertor

import androidx.room.TypeConverter
import com.example.reviewlistapp.data.datasource.model.Reviews
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable


class ReviewsTypeConverters : Serializable {

    @TypeConverter
    fun fromOptionValuesList(optionValues: Array<Reviews>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<Array<Reviews>>() {

        }.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter
    fun toOptionValuesList(optionValuesString: String?): Array<Reviews>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<Array<Reviews>>() {

        }.type
        return gson.fromJson(optionValuesString, type)
    }

}