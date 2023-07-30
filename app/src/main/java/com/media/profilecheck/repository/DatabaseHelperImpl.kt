package com.media.profilecheck.repository

import android.media.CamcorderProfile.getAll
import com.media.profilecheck.models.UsersResult
import com.media.profilecheck.room.DatabaseHelper
import com.media.profilecheck.room.UserDatabase

class DatabaseHelperImpl (private val userDatabase: UserDatabase) : DatabaseHelper{

    override suspend fun getUsers(): List<UsersResult> = userDatabase.getUserDao.getUserData()

}