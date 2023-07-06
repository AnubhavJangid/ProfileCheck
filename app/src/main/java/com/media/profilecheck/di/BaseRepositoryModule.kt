package com.media.profilecheck.di

import com.media.profilecheck.repository.MainRepoInterface
import com.media.profilecheck.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class BaseRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRepository(mainRepository: MainRepositoryImpl) : MainRepoInterface

}