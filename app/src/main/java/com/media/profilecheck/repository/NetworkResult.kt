package com.media.profilecheck.repository

import com.bumptech.glide.load.engine.Resource
import okhttp3.ResponseBody

sealed class NetworkResult<out T> {
    class Success<out T>(val data: T) : NetworkResult<T>()
    class Failure (
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : NetworkResult<Nothing>()
    class Loading<T> : NetworkResult<T>()
}

