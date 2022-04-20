package com.media.profilecheck.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException



abstract class BaseRepository {

    suspend fun<T> safeApiCall(
        apiCall : suspend () -> T
    ) : NetworkResult<T>
    {
        Log.e("ApiCall", ""+apiCall.invoke())
        return withContext(Dispatchers.IO){
            try {
                Log.e("ApiCall" , ""+apiCall.invoke())
                NetworkResult.Success(apiCall.invoke())
            }catch (throwable : Throwable){
                when(throwable)
                {
                    is HttpException -> {
                        Log.e("Error", ""+throwable.response()?.errorBody())
                        NetworkResult.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }else ->{
                        NetworkResult.Failure(true, null,null)
                    }
                }
            }
        }
    }
}