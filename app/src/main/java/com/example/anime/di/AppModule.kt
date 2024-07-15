package com.example.anime.di

import android.app.Application
import com.example.anime.data.manager.LocalUserManagerImpl
import com.example.anime.domian.manager.LocalUserManager
import com.example.anime.domian.useCases.AppEntryUseCases
import com.example.anime.domian.useCases.ReadAppEntry
import com.example.anime.domian.useCases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) : AppEntryUseCases = AppEntryUseCases(
        saveAppEntry = SaveAppEntry(localUserManager),
        readAppEntry = ReadAppEntry(localUserManager)
    )
}