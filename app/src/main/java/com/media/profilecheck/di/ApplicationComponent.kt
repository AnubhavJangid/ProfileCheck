package com.media.profilecheck.di

import com.media.profilecheck.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, BaseRepositoryModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

}