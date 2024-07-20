package com.example.anime.di

import android.app.Application
import com.example.anime.data.manager.LocalUserManagerImpl
import com.example.anime.data.network.KitsuApi
import com.example.anime.data.repository.KitsuRepositoryImpl
import com.example.anime.domian.manager.LocalUserManager
import com.example.anime.domian.repository.KitsuRepository
import com.example.anime.domian.useCases.AppEntryUseCases
import com.example.anime.domian.useCases.ReadAppEntry
import com.example.anime.domian.useCases.SaveAppEntry
import com.example.anime.util.Constants.BASE_URL
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provides LocalUserManager
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImpl(application)

    // Provides AppEntryUseCases
    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) : AppEntryUseCases = AppEntryUseCases(
        saveAppEntry = SaveAppEntry(localUserManager),
        readAppEntry = ReadAppEntry(localUserManager)
    )

    // Provides Moshi
    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    // Provides Kitsu Api
    @Singleton
    @Provides
    fun provideKitsuApi(moshi: Moshi): KitsuApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
            .create(KitsuApi::class.java)

    // Provide Kitsu Repository
    @Singleton
    @Provides
    fun provideKitsuRepository(api: KitsuApi): KitsuRepository =
        KitsuRepositoryImpl(api = api)
}