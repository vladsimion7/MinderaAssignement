package com.vlad.simion.minderaassignment.di

import com.vlad.simion.minderaassignment.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @Singleton
    @Named("isDebug")
    fun provideIsDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    @Provides
    @Singleton
    fun provideDispatchers(): DispatcherProvider{
        return DefaultDispatchers()
    }
}