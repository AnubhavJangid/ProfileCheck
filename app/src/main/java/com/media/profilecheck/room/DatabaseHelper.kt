package com.media.profilecheck.room

import com.media.profilecheck.models.UsersResult

interface DatabaseHelper {
    suspend fun getUsers() : List<UsersResult>
}