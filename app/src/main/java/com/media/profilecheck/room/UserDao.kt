package com.media.profilecheck.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.media.profilecheck.models.UsersResult
import com.media.profilecheck.repository.UserData

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(user: UserData)

    @Query("Select * FROM userData")
    fun getUserData() : LiveData<List<UserData>>
}