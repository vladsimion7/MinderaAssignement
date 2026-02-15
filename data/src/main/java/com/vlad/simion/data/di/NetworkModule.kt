package com.vlad.simion.data.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.vlad.simion.data.network.GithubApi
import com.vlad.simion.data.network.mock.MockGithubApi
import com.vlad.simion.data.util.AppConfig
import com.vlad.simion.domain.common.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CONNECT_TIMEOUT_SECONDS = 30L
    private const val READ_TIMEOUT_SECONDS = 60L
    private const val WRITE_TIMEOUT_SECONDS = 60L

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named("isDebug") isDebug: Boolean,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (isDebug) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                },
            )
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        @Named("baseUrl") baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setPrettyPrinting()
                        .create(),
                ),
            )
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideGithubApi(
        @ApplicationContext context: Context,
        retrofit: Retrofit,
        preferencesManager: PreferenceManager
    ): GithubApi {
        return if (AppConfig.isMockEnabled) {
            MockGithubApi(context)
        } else {
            retrofit.create(GithubApi::class.java)
        }
    }
}