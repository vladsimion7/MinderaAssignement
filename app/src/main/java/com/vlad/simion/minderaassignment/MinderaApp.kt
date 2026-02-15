package com.vlad.simion.minderaassignment

import android.app.Application
import com.vlad.simion.data.util.AppConfig
import com.vlad.simion.domain.common.DataSourceType
import com.vlad.simion.domain.common.PreferenceManager
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class MinderaApp : Application() {

    @Inject
    lateinit var preferencesManager: PreferenceManager

    override fun onCreate() {
        super.onCreate()

        runBlocking {
            AppConfig.isMockEnabled = preferencesManager.getDataSourceType() == DataSourceType.MOCK
        }
    }

}