package com.idd.openweatherapp.api

import retrofit2.Response
import java.io.IOException

/**
 * Created by ignaciodeandreisdenis on 8/10/20.
 */

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiResponse<T> {
            return if (error is IOException) {
                ApiNetworkErrorResponse()
            } else {
                ApiErrorResponse(error.message ?: "unknown error")
            }
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body = body)
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(errorMsg ?: "unknown error")
            }
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(
    val errorMessage: String
) : ApiResponse<T>()

class ApiNetworkErrorResponse<T> : ApiResponse<T>()