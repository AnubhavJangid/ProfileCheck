package com.media.profilecheck.repository

import com.media.profilecheck.api.ApiService
import com.media.profilecheck.models.UserResponse
import com.media.profilecheck.room.UserDatabase
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(private val apiService : ApiService) : MainRepoInterface,BaseRepository() {

    override suspend fun getUserData(number : Int): NetworkResult<UserResponse> {
        return safeApiCall {
            apiService.getUsers(number)
        }
    }

}


