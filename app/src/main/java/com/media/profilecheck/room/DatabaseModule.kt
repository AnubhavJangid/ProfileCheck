package com.media.profilecheck.room

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideUserDB(context : Context) : UserDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            "User Database"
        ).build()
    }

    //We are going to create a factory for the application context because this value comes in the runtime
    //For the runtime values we are going to create a factory for that.


}