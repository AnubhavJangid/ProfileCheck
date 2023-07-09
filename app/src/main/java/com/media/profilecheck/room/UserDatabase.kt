package com.media.profilecheck.room

import android.content.Context
import android.service.autofill.UserData
import androidx.room.*
import com.media.profilecheck.models.UserResponse
import com.media.profilecheck.models.UsersResult

@Database(entities = [UsersResult::class], version = 1)
@TypeConverters(Converter::class)
abstract class UserDatabase : RoomDatabase() {

    abstract val getUserDao : UserDao

}