package com.media.profilecheck.di

import com.media.profilecheck.repository.MainRepository
import dagger.Component
import dagger.Provides

@Component
interface ApiMethods {

    fun getMainRepository() : MainRepository


}