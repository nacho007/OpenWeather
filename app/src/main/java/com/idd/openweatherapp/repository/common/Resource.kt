package com.idd.openweatherapp.repository.common

import com.idd.openweatherapp.repository.common.Status

/**
 * Created by ignaciodeandreisdenis on 8/10/20.
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                msg
            )
        }

        fun <T> networkError(data: T?): Resource<T> {
            return Resource(
                Status.NETWORK_ERROR,
                data,
                null
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}