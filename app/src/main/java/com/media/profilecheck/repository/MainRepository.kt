package com.media.profilecheck.repository

import android.util.Log
import com.media.profilecheck.api.ApiService
import com.media.profilecheck.models.UserResponse
import com.media.profilecheck.room.UserDatabase
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService : ApiService) : BaseRepository() {

    suspend fun getUsersList(number : Int): NetworkResult<UserResponse> {

        return safeApiCall {
            apiService.getUsers(number)
        }
    }


}


