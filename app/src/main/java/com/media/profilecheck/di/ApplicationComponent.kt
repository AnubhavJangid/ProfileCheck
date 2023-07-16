package com.media.profilecheck.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.media.profilecheck.MainActivity
import com.media.profilecheck.room.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, BaseRepositoryModule::class, DatabaseModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun getMap() : Map<Class<*>, ViewModel>

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context : Context) : ApplicationComponent
    }
}