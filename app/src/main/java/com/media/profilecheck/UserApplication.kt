package com.media.profilecheck

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}