package com.vlad.simion.data.di

import android.content.Context
import com.vlad.simion.data.network.GithubApi
import com.vlad.simion.data.preference.PreferencesManagerImpl
import com.vlad.simion.data.repository.GithubRepositoryImpl
import com.vlad.simion.domain.common.PreferenceManager
import com.vlad.simion.domain.repository.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubRepository(api: GithubApi): GithubRepository {
        return GithubRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providePreferenceManager(@ApplicationContext context: Context): PreferenceManager {
        return PreferencesManagerImpl(context)
    }
}
