package com.media.profilecheck.room

import android.content.Context
import androidx.room.*
import com.media.profilecheck.models.Name
import com.media.profilecheck.models.UserResponse
import com.media.profilecheck.models.UsersResult
import com.media.profilecheck.repository.UserData

@Database(entities = [UserData::class], version = 1)
@TypeConverters(Converter::class)
abstract class UserDatabase : RoomDatabase() {

    abstract val userDao : UserDao

    companion object
    {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDbInstance(context: Context): UserDatabase {
            if (INSTANCE == null)
            {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "User Database"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}