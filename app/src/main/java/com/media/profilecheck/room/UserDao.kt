package com.media.profilecheck.room

import android.service.autofill.UserData
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.media.profilecheck.models.UsersResult

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(users : List<UsersResult>)

    @Query("Select * FROM UsersResult")
    fun getUserData() : List<UsersResult>
}