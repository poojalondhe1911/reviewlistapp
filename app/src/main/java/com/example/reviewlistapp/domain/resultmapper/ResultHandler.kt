package com.example.reviewlistapp.domain.resultmapper

import com.example.reviewlistapp.data.datasource.model.Review
import java.net.SocketTimeoutException

open class ResultHandler {
    fun <T : Any> handleSuccess(data: T): Result<T> {
        return Result.success(data)
    }

    fun <T : Any> handleException(e: Exception): Result<Review>? {
        return when (e) {
            //is HttpException -> Resource.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Result.error(
                getErrorMessage(ErrorCodes.SocketTimeOut.code),
                null
            )
            else -> Result.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }
}

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}
