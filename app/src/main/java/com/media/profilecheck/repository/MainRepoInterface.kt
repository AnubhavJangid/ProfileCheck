package com.media.profilecheck.repository

import com.media.profilecheck.models.UserResponse

interface MainRepoInterface {
    suspend fun getUserData(number: Int): NetworkResult<UserResponse>
}