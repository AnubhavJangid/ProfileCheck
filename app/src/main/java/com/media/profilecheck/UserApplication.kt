package com.media.profilecheck

import android.app.Application
import com.media.profilecheck.di.ApplicationComponent
import com.media.profilecheck.di.DaggerApplicationComponent

class UserApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}