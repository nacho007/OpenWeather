package com.idd.openweatherapp.utils

import androidx.lifecycle.LiveData

/**
 * Created by ignaciodeandreisdenis on 8/10/20.
 */
class AbsentLiveData<T : Any?> private constructor() : LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}
